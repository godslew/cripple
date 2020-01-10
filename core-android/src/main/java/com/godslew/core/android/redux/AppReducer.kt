package com.godslew.core.android.redux

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.state.AppState

object AppReducer : Reducer<AppState, AppAction> {
  override fun invoke(state: AppState, action: AppAction): AppState {
    return when (action) {
      is AppAction.SessionAction -> {
        state.copy(sessionState = SessionReducer.invoke(state.sessionState, action))
      }
    }
  }
}