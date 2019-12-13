package com.godslew.cripple.domain.store

import com.freeletics.rxredux.Reducer
import com.godslew.cripple.domain.action.ActionType
import com.godslew.cripple.domain.state.StateType
import io.reactivex.disposables.Disposable

class AppStore<STATE: StateType, ACTION: ActionType, REDUCER: Reducer<STATE, ACTION>> (
  private val disposable: Disposable,
  private val initialState: STATE,
  private val reducer: REDUCER
) {

}