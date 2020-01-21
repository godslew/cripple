package com.godslew.timeline

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.entity.TimelinePage
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.domain.store.AppStore
import com.godslew.core.domain.usecase.TimelineUseCase
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class TimelineViewModel @Inject constructor(
  application: Application,
  private val appStore: AppStore,
  private val timelineUseCase: TimelineUseCase
) : AndroidViewModel(application) {
  private val disposable = CompositeDisposable()

  val loadTimeline: PublishRelay<List<TimelinePage>> = PublishRelay.create()

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

  fun setup() {
    appStore.observable()
      .map { it.accountStates }
      .distinctUntilChanged()
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .bindTo {
        Timber.d("snake00 $it")
        loadTimeline.accept(
          it.flatMap { accountState ->
            accountState.pages.map { timelineState ->
              TimelinePage(
                timelineState.page,
                timelineState.account
              )
            }
          })
      }.addTo(disposable)

    timelineUseCase.loadTimeline()
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .bindTo {
        Timber.d("snake00 $it")
        if (it.isNotEmpty()) {
          AppDispatcher.dispatch(AppAction.AccountAction.LoadTimelineAction(it))
        }
      }.addTo(disposable)
  }
}
