package com.godslew.core.domain.repository

import com.godslew.core.android.entity.TimelinePage
import com.godslew.core.android.entity.TimelinePages
import com.godslew.gksettingpreferences.SettingPreferences
import io.reactivex.Single
import javax.inject.Inject

class TimelinePageRepository @Inject constructor(
  private val settingPreferences: SettingPreferences
) {

  fun getPages() : Single<List<TimelinePage>> {
    return Single.create {
      val pages = settingPreferences.load(TimelineStoreName, TimelinePages::class.java)
      if (pages == null) {
        it.onSuccess(mutableListOf())
        return@create
      }
      it.onSuccess(pages.list)
    }

  }

  fun save(pages : List<TimelinePage>) {
    settingPreferences.save(TimelineStoreName, TimelinePages(pages))
  }

  companion object {
    private const val TimelineStoreName = "cripple_timeline"
  }
}