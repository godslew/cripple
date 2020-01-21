package com.godslew.core.domain.state.viewstate

import com.godslew.core.android.redux.StateType

data class AppViewState(
  val mainViewState: MainViewState,
  val homeTabState : HomeTabState
) : StateType {
  companion object {
    fun initial(): AppViewState {
      return AppViewState(
        MainViewState.initial(),
        HomeTabState.initial()
      )
    }
  }
}