package com.godslew.core.domain.state

import com.godslew.core.android.redux.StateType
import com.godslew.core.java.entity.CrippleStatus

data class DomainState(
  val statuses : List<CrippleStatus>
) : StateType {
  companion object {
    fun initial() : DomainState {
      return DomainState(statuses = mutableListOf())
    }
  }
}