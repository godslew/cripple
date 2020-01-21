package com.godslew.core.android.store

import com.godslew.core.android.action.AppAction
import com.godslew.core.android.redux.ActionType
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.android.redux.AppReducer
import com.godslew.core.domain.state.AppState
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable


class AppStore (
  private val disposable: CompositeDisposable,
  private val initialState: AppState,
  private val reducer: AppReducer
) {

  private val relay = BehaviorRelay.createDefault(initialState)

  init {
    AppDispatcher.on(ActionType::class.java)
      .map { action ->
        val state = relay.value
        reducer.invoke(state, action as AppAction)
      }
      .bindTo { relay.accept(it) }
      .addTo(disposable)
  }

  fun observable(): Observable<AppState> {
    return relay
  }

  fun getState(): AppState {
    return relay.value
  }

  fun setState(state: AppState) {
    relay.accept(state)
  }

  fun single(): Single<AppState> {
    return observable().take(1).singleOrError()
  }

}