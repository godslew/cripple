package com.godslew.cripple.presenter.timeline

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.godslew.cripple.domain.entity.TimelinePage
import com.godslew.cripple.domain.value.PageType

class TimelineAdapter(
  fragmentActivity: FragmentActivity,
  private val pages: List<TimelinePage>
) : FragmentStateAdapter(fragmentActivity) {

  override fun getItemCount(): Int {
    return pages.size
  }

  override fun createFragment(position: Int): Fragment {
    return when (pages[position].pageType) {
      PageType.HOME -> {
        HomeFragment()
      }
      PageType.MENTION -> {
        HomeFragment()
      }
      PageType.LIKE -> {
        HomeFragment()
      }
      PageType.DM -> {
        HomeFragment()
      }
      PageType.SEARCH -> {
        HomeFragment()
      }
      PageType.USER -> {
        HomeFragment()
      }
    }
  }
}


