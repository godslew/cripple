package com.godslew.core.domain.state

import com.godslew.core.android.redux.StateType
import com.godslew.core.domain.state.viewstate.AppViewState
import com.godslew.core.java.entity.Account

data class AppState(
  val current : Account,
  val accountList: List<Account>,
  val sessionState: SessionState,
  val viewState: AppViewState
) : StateType {
  companion object {
    fun initial(): AppState {
      return AppState(
        Account.initialize(),
        accountList = emptyList(),
        sessionState = SessionState.initial(),
        viewState = AppViewState.initial()
      )
    }
  }

}