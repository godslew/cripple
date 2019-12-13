package com.godslew.cripple.domain.state

import com.godslew.cripple.domain.entity.Account
import twitter4j.auth.AccessToken

data class SessionState(
  val account: Account
) : StateType {
  companion object {
    fun initial(): SessionState {
      return SessionState(
        account = Account("", "", AccessToken("",""))
      )
    }
  }
}