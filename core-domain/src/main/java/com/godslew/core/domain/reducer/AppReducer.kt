package com.godslew.core.domain.reducer

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.domain.state.AccountState
import com.godslew.core.domain.state.AppState
import com.godslew.core.domain.state.TimelineState

object AppReducer : Reducer<AppState, AppAction> {
  override fun invoke(state: AppState, action: AppAction): AppState {
    return when (action) {
      is AppAction.AccountAction -> {
        when (action) {
          is AppAction.AccountAction.RegisterAction -> state.copy(accountList = action.accounts)
          is AppAction.AccountAction.ChangeCurrentAction -> state.copy(current = action.account)
          is AppAction.AccountAction.LoadAccountAction -> state.copy(accountList = action.accounts)
          is AppAction.AccountAction.TimelineAction -> {
            state.copy(accountStates = state.accountStates.map {
              TimelineReducer.invoke(
                it,
                action
              )
            })
          }
          is AppAction.AccountAction.LoadTimelineAction -> {
            val accounts = action.pages.map { it.account }.distinctBy { it.userId() }
            state.copy(accountStates = accounts.map {
              AccountState(
                account = it,
                pages = action.pages.filter { page -> page.account.userId() == it.userId() }.map { page ->
                  TimelineState(
                    page = page.pageType,
                    account = it,
                    statuses = mutableListOf()
                  )
                })
            })
          }
        }
      }
      is AppAction.AppViewAction -> state.copy(
        viewState = AppViewReducer.invoke(
          state.viewState,
          action
        )
      )
    }
  }
}
