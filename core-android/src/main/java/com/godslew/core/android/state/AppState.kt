package com.godslew.core.android.state

import com.godslew.core.android.redux.StateType
import com.godslew.core.java.entity.Account

data class AppState(
  val index : Int,
  val accountList: List<Account>,
  val sessionState: SessionState
) : StateType {
  companion object {
    fun initial(): AppState {
      return AppState(
        index = 0,
        accountList = emptyList(),
        sessionState = SessionState.initial()
      )
    }
  }

}