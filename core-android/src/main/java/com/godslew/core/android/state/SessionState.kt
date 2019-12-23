package com.godslew.core.android.state

import com.godslew.core.android.redux.StateType
import com.godslew.core.java.entity.Account
import twitter4j.auth.AccessToken

data class SessionState(
  val account: Account
) : StateType {
  companion object {
    fun initial(): SessionState {
      return SessionState(
        account = Account(
          "",
          "",
          AccessToken("", "")
        )
      )
    }
  }
}