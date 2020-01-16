package com.godslew.core.android.entity

import com.godslew.core.java.entity.Account
import com.godslew.core.java.value.PageType
import com.godslew.gksettingpreferences.SettingDataType

data class TimelinePage(
  val pageType : PageType,
  val account: Account
) : SettingDataType