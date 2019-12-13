package com.godslew.cripple.domain.reducer

import com.freeletics.rxredux.Reducer
import com.godslew.cripple.domain.action.AppAction
import com.godslew.cripple.domain.state.AppState

object AppReducer : Reducer<AppState, AppAction> {
  override fun invoke(state: AppState, action: AppAction): AppState {
    return when (action) {
      is AppAction.SessionAction -> {
        state.copy(sessionState = SessionReducer.invoke(state.sessionState, action))
      }
    }
  }
}
