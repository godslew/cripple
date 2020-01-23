package com.godslew.timeline

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.domain.store.AppStore
import com.godslew.core.domain.usecase.TwitterUseCase
import com.godslew.core.java.entity.Account
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.core.java.value.PageType
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MentionViewModel @Inject constructor(
  application: Application,
  private val appStore: AppStore,
  private val twitterUseCase: TwitterUseCase
) : AndroidViewModel(application) {
  private val disposable = CompositeDisposable()

  val fetchStatuses : PublishRelay<List<CrippleStatus>> = PublishRelay.create()
  val isFetching : BehaviorRelay<Boolean> = BehaviorRelay.createDefault(false)

  private var maxId : Long = 0
  private var sinceId : Long = 0

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

  fun setup(account: Account) {
    appStore.observable()
      .map { it.accountStates.first { accountState ->  accountState.account.userId() == account.userId() } }
      .map { it.pages.first{ timelineState -> timelineState.page == PageType.MENTION } }
      .map { it.statuses }
      .distinctUntilChanged()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
        fetchStatuses.accept(it)
      }.addTo(disposable)

    // ToDo タブ追加の時にStoreから取得しに行ってなければAPIを叩くようにする
    isFetching.accept(true)
    twitterUseCase.getMentions(account)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
        isFetching.accept(false)
        if (it.isNotEmpty()) {
          sinceId = it.first().status.id
          maxId = it.last().status.id
          AppDispatcher.dispatch(AppAction.AccountAction.TimelineAction.StatusAction.AddTopAction(PageType.MENTION, it))
        }
      }.addTo(disposable)
  }

  fun fetchTop(account: Account) {
    if (sinceId == 0L) {
      isFetching.accept(false)
      return
    }
    isFetching.accept(true)
    twitterUseCase.getMentionsBySinceId(account, sinceId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
        isFetching.accept(false)
        if (it.isNotEmpty()) {
          sinceId = it.first().status.id
          AppDispatcher.dispatch(AppAction.AccountAction.TimelineAction.StatusAction.AddTopAction(PageType.MENTION, it))
        }
      }.addTo(disposable)

  }

  fun fetchBottom(account: Account) {
    if (maxId == 0L) {
      isFetching.accept(true)
      return
    }
    isFetching.accept(false)
    twitterUseCase.getMentionsByMaxId(account, maxId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
        if (it.isNotEmpty()) {
          maxId = it.last().status.id
          AppDispatcher.dispatch(AppAction.AccountAction.TimelineAction.StatusAction.AddBottomAction(PageType.MENTION, it))
        }
      }.addTo(disposable)
  }
}
