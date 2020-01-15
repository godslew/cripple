package com.godslew.core.android.action

import com.godslew.core.android.redux.ActionType
import com.godslew.core.java.entity.Account
import twitter4j.Status

sealed class AppAction : ActionType {
  sealed class AccountAction : AppAction() {
    data class RegisterAction(val accounts : List<Account>) : AccountAction()
    data class ChangeAction(val account: Account) : AccountAction()
    data class Load(val accounts : List<Account>) : AccountAction()
  }
  sealed class SessionAction : AppAction() {
    sealed class TweetAction : SessionAction() {
      data class SendTweet(val status: Status) : TweetAction()
    }
  }
}