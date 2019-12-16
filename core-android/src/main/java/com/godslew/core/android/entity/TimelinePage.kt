package com.godslew.core.android.entity

import com.godslew.core.java.entity.Account
import com.godslew.gksettingpreferences.SettingDataType

data class TimelinePage(
  val pageType : com.godslew.core.java.value.PageType,
  val account: Account
) : SettingDataType