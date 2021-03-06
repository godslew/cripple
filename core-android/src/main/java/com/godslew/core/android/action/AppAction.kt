package com.godslew.core.android.action

import com.godslew.core.android.entity.TimelinePage
import com.godslew.core.android.redux.ActionType
import com.godslew.core.java.entity.Account
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.core.java.value.PageType

sealed class AppAction : ActionType {
  sealed class AccountAction : AppAction() {
    data class RegisterAction(val accounts : List<Account>) : AccountAction()
    data class ChangeCurrentAction(val account: Account) : AccountAction()
    data class LoadAccountAction(val accounts : List<Account>) : AccountAction()
    data class LoadTimelineAction(val pages : List<TimelinePage>) : AccountAction()
    sealed class TimelineAction : AccountAction() {
      data class AddAction(val page : TimelinePage) : TimelineAction()
      data class RemoveAction(val page : TimelinePage) : TimelineAction()
      sealed class StatusAction : TimelineAction() {
        data class AddTopAction(val type : PageType, val statuses : List<CrippleStatus>) : StatusAction()
        data class AddBottomAction(val type : PageType, val statuses : List<CrippleStatus>) : StatusAction()
        data class RefreshAction(val status: CrippleStatus) : StatusAction()
      }
    }
  }
  //sealed class DomainAction : AppAction() {
  //  data class PutStatuses(val statuses: List<CrippleStatus>) : DomainAction()
  //  data class PutStatus(val status : CrippleStatus) : DomainAction()
  //}
  sealed class AppViewAction : AppAction()
}