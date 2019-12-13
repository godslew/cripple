package com.godslew.cripple.domain.entity

import com.godslew.cripple.domain.value.PageType
import com.godslew.gksettingpreferences.SettingDataType

data class TimelinePage(
  val pageType : PageType,
  val account: Account
) : SettingDataType