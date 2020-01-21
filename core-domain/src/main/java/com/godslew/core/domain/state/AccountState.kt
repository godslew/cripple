package com.godslew.core.domain.state

import com.godslew.core.android.redux.StateType
import com.godslew.core.java.entity.Account
import com.godslew.core.java.entity.CrippleStatus
import twitter4j.auth.AccessToken

data class AccountState(
  val account: Account,
  val pages : List<TimelineState>
) : StateType
