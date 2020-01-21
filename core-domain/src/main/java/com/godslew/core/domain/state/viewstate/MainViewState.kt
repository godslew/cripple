package com.godslew.core.domain.state.viewstate

import com.godslew.core.android.redux.StateType

data class MainViewState (
  val isShowTweetButton : Boolean
) : StateType {
  companion object {
    fun initial() : MainViewState {
      return MainViewState(true)
    }
  }
}