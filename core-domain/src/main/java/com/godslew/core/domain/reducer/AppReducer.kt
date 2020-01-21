package com.godslew.core.domain.reducer

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.domain.state.AppState

object AppReducer : Reducer<AppState, AppAction> {
  override fun invoke(state: AppState, action: AppAction): AppState {
    return when (action) {
      is AppAction.AccountAction -> {
        when(action) {
          is AppAction.AccountAction.RegisterAction -> state.copy(accountList = action.accounts)
          is AppAction.AccountAction.ChangeCurrentAction -> state.copy(current = action.account)
          is AppAction.AccountAction.LoadAction -> state.copy(accountList = action.accounts)
          is AppAction.AccountAction.TimelineAction -> {
            state.copy(accountStates = state.accountStates.map { TimelineReducer.invoke(it, action) })
          }
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
