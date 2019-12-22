package com.godslew.core.android.store

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.redux.ActionType
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.android.redux.StateType
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo


class AppStore<STATE: StateType, ACTION: ActionType, REDUCER: Reducer<STATE, ACTION>> (
  private val disposable: CompositeDisposable,
  private val initialState: STATE,
  private val reducer: REDUCER
) {

  private val relay = BehaviorRelay.createDefault(initialState)

  init {
    AppDispatcher.on(ActionType::class.java)
      .map { action ->
        val state = relay.value
        reducer.invoke(state, action as ACTION)
      }
      .bindTo { relay.accept(it) }
      .addTo(disposable)
  }

  fun observable(): Observable<STATE> {
    return relay
  }

  fun getState(): STATE {
    return relay.value
  }

  fun setState(state: STATE) {
    relay.accept(state)
  }

  fun single(): Single<STATE> {
    return observable().take(1).singleOrError()
  }

}