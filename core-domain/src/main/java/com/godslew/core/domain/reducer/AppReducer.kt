package com.godslew.core.domain.reducer

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.domain.state.AppState

object AppReducer : Reducer<AppState, AppAction> {
  override fun invoke(state: AppState, action: AppAction): AppState {
    return when (action) {
      is AppAction.SessionAction -> {
        state.copy(sessionState = SessionReducer.invoke(
          state.sessionState,
          action
        )
        )
      }
      is AppAction.AccountAction -> {
        when(action) {
          is AppAction.AccountAction.RegisterAction -> state.copy(accountList = action.accounts)
          is AppAction.AccountAction.ChangeCurrentAction -> state.copy(current = action.account)
          is AppAction.AccountAction.Load -> state.copy(accountList = action.accounts)
        }
      }
      is AppAction.AppViewAction -> state.copy(viewState = AppViewReducer.invoke(
        state.viewState,
        action
      )
      )
    }
  }
}
