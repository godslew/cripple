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
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  application: Application,
  private val appStore: AppStore,
  private val twitterUseCase: TwitterUseCase
) : AndroidViewModel(application) {
  private val disposable = CompositeDisposable()

  val fetchStatuses : PublishRelay<List<CrippleStatus>> = PublishRelay.create()

  private var maxId : Long = 0
  private var sinceId : Long = 0

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

  fun setup(account: Account) {
    appStore.observable()
      .map { it.accountStates.first { accountState ->  accountState.account.userId() == account.userId() } }
      .map { it.pages.first{ timelineState -> timelineState.page == PageType.HOME } }
      .map { it.statuses }
      .distinctUntilChanged()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
        fetchStatuses.accept(it)
      }.addTo(disposable)

    twitterUseCase.getHomeTimeline(account)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
        if (it.isNotEmpty()) {
          sinceId = it.first().status.id
          maxId = it.last().status.id
          AppDispatcher.dispatch(AppAction.AccountAction.TimelineAction.StatusAction.AddTopAction(it))
        }
      }.addTo(disposable)
  }

  fun fetchTop(account: Account) {
    if (sinceId == 0L) {
      return
    }
    twitterUseCase.getHomeTimelineByMaxId(account, sinceId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
        if (it.isNotEmpty()) {
          sinceId = it.first().status.id
          AppDispatcher.dispatch(AppAction.AccountAction.TimelineAction.StatusAction.AddTopAction(it))
        }
      }.addTo(disposable)

  }

  fun fetchBottom(account: Account) {
    if (maxId == 0L) {
      return
    }
    twitterUseCase.getHomeTimelineBySinceId(account, maxId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
        if (it.isNotEmpty()) {
          maxId = it.last().status.id
          AppDispatcher.dispatch(AppAction.AccountAction.TimelineAction.StatusAction.AddBottomAction(it))
        }
      }.addTo(disposable)
  }
}
