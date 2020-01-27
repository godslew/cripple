package com.godslew.timeline.creator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.domain.store.AppStore
import com.godslew.core.domain.usecase.TimelineUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TimelineCreatorViewModel @Inject constructor(
  application: Application,
  private val appStore: AppStore,
  private val timelineUseCase: TimelineUseCase
) : AndroidViewModel(application) {

  private val disposable  = CompositeDisposable()

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

  fun setup() {
  }
}