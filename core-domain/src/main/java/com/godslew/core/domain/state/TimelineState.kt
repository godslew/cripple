package com.godslew.core.domain.state

import com.godslew.core.android.redux.StateType
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.core.java.value.PageType

data class TimelineState(
  val page : PageType,
  val statuses : List<CrippleStatus>
) : StateType