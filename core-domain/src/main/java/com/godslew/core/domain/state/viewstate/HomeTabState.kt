package com.godslew.core.domain.state.viewstate

import com.godslew.core.android.entity.TimelinePage
import com.godslew.core.android.redux.StateType

data class HomeTabState (
  val pages : List<TimelinePage>
) : StateType {

  companion object {
    fun initial() : HomeTabState {
      return HomeTabState(pages = mutableListOf())
    }
  }
}