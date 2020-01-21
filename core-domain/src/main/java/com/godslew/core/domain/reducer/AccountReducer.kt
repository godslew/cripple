package com.godslew.core.domain.reducer

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.domain.state.AccountState
import com.godslew.core.domain.state.TimelineState

object TimelineReducer : Reducer<AccountState, AppAction.AccountAction.TimelineAction>{
  override fun invoke(state: AccountState, action: AppAction.AccountAction.TimelineAction): AccountState {
    return when (action) {
      is AppAction.AccountAction.TimelineAction.Load -> state.copy(pages = action.pages.map { TimelineState(page = it.pageType, statuses = mutableListOf()) })
      is AppAction.AccountAction.TimelineAction.Add -> state.copy(pages = state.pages.plus(TimelineState(page = action.page.pageType, statuses = mutableListOf())))
    }
  }
}