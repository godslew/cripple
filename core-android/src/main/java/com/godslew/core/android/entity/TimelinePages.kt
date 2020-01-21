package com.godslew.core.android.entity

import com.godslew.gksettingpreferences.SettingDataType

data class TimelinePages(
  val list : List<TimelinePage>
  ) : SettingDataType {
  companion object {
    fun initialize() : TimelinePages {
      return TimelinePages(list = mutableListOf())
    }
  }
}