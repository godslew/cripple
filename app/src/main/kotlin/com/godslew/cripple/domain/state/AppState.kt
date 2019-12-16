package com.godslew.cripple.domain.state

import com.godslew.cripple.domain.entity.Account

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