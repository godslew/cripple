package com.godslew.core.domain.state

import com.godslew.core.android.redux.StateType
import com.godslew.core.java.entity.Account
import com.godslew.core.java.entity.CrippleStatus
import twitter4j.auth.AccessToken

data class SessionState(
  val account: Account,
  val statuses : List<CrippleStatus>
) : StateType {
  companion object {
    fun initial(): SessionState {
      return SessionState(
        account = Account(
          "",
          "",
          AccessToken("", "")
        ),
        statuses = mutableListOf()
      )
    }
  }
}