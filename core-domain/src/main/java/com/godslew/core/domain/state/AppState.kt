package com.godslew.core.domain.state

import com.godslew.core.android.redux.StateType
import com.godslew.core.domain.state.viewstate.AppViewState
import com.godslew.core.java.entity.Account

data class AppState(
  val current : Account,
  val accountList: List<Account>,
  val accountStates: List<AccountState>,
  val domainState: DomainState,
  val viewState: AppViewState
) : StateType {
  companion object {
    fun initial(): AppState {
      return AppState(
        Account.initialize(),
        accountList = emptyList(),
        accountStates = emptyList(),
        domainState = DomainState.initial(),
        viewState = AppViewState.initial()
      )
    }
  }

}