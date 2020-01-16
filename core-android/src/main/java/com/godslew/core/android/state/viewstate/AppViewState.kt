package com.godslew.core.android.state.viewstate

import com.godslew.core.android.redux.StateType

data class AppViewState(
  val mainViewState: MainViewState
) : StateType {
  companion object {
    fun initial(): AppViewState {
      return AppViewState(
        MainViewState.initial()
      )
    }
  }
}