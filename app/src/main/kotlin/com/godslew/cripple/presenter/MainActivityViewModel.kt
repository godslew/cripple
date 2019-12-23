package com.godslew.cripple.presenter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.redux.AppReducer
import com.godslew.core.android.state.AppState
import com.godslew.core.android.store.AppStore
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
  application: Application,
  appStore: AppStore<AppState, AppAction, AppReducer>
) : AndroidViewModel(application) {
  private val disposable  = CompositeDisposable()

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }
}