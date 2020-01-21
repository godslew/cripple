package com.godslew.timeline

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.godslew.core.android.entity.TimelinePage
import com.godslew.core.java.value.PageType

class TimelineAdapter(
  fragmentActivity: FragmentActivity,
  private val pages: List<TimelinePage>
) : FragmentStateAdapter(fragmentActivity) {

  override fun getItemCount(): Int {
    return pages.size
  }

  override fun createFragment(position: Int): Fragment {
    val page = pages[position]
    return when (page.pageType) {
      PageType.HOME -> {
        HomeFragment(page.account)
      }
      PageType.MENTION -> {
        HomeFragment(page.account)
      }
      PageType.LIKE -> {
        HomeFragment(page.account)
      }
      PageType.DM -> {
        HomeFragment(page.account)
      }
      PageType.SEARCH -> {
        HomeFragment(page.account)
      }
      PageType.USER -> {
        HomeFragment(page.account)
      }
    }
  }
}


