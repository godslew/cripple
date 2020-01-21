package com.godslew.core.domain.reducer

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.domain.state.AccountState
import com.godslew.core.domain.state.TimelineState

object StatusReducer : Reducer<TimelineState, AppAction.AccountAction.TimelineAction.StatusAction> {
  override fun invoke(
    state: TimelineState,
    action: AppAction.AccountAction.TimelineAction.StatusAction
  ): TimelineState {
    return when (action) {
      is AppAction.AccountAction.TimelineAction.StatusAction.AddBottomAction -> state.copy(statuses = action.statuses + state.statuses)
      is AppAction.AccountAction.TimelineAction.StatusAction.AddTopAction -> state.copy(statuses = state.statuses + action.statuses)
      is AppAction.AccountAction.TimelineAction.StatusAction.RefreshAction -> state.copy(statuses = state.statuses.map {
        if (it.status.id == action.status.status.id) {
          action.status
        } else {
          it
        }
      })
    }
  }
}