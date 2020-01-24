package com.godslew.timeline.creator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.domain.store.AppStore
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TimelineCreatorViewModel @Inject constructor(
  application: Application,
  private val appStore: AppStore
) : AndroidViewModel(application) {

  private val disposable  = CompositeDisposable()

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }
}