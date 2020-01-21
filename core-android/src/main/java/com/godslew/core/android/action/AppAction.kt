package com.godslew.core.android.action

import com.godslew.core.android.entity.TimelinePage
import com.godslew.core.android.redux.ActionType
import com.godslew.core.java.entity.Account
import twitter4j.Status

sealed class AppAction : ActionType {
  sealed class AccountAction : AppAction() {
    data class RegisterAction(val accounts : List<Account>) : AccountAction()
    data class ChangeCurrentAction(val account: Account) : AccountAction()
    data class Load(val accounts : List<Account>) : AccountAction()
    sealed class TimelineAction : AccountAction() {
      data class Load(val pages : List<TimelinePage>) : TimelineAction()
      data class Add(val page : TimelinePage) : TimelineAction()
    }
  }
  sealed class AppViewAction : AppAction() {
    sealed class MainViewAction : AppViewAction() {
      data class DisplayTweetButtonAction(val isShow: Boolean) : MainViewAction()
    }
  }
}