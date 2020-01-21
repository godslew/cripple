package com.godslew.core.domain.usecase

import com.godslew.core.android.entity.TimelinePage
import com.godslew.core.domain.repository.TimelinePageRepository
import com.godslew.core.java.entity.Account
import com.godslew.core.java.value.PageType
import io.reactivex.Single
import javax.inject.Inject

class TimelineUseCase @Inject constructor(
  private val timelinePageRepository: TimelinePageRepository
){
  fun loadTimeline() : Single<List<TimelinePage>> {
    return timelinePageRepository.getPages()
  }

  fun saveTimeline(pages : List<TimelinePage>) {
    timelinePageRepository.save(pages)
  }

  fun initializeTimeline(account: Account) : List<TimelinePage> {
    return listOf(
      TimelinePage(PageType.HOME, account),
      TimelinePage(PageType.MENTION, account)
    )
  }
}