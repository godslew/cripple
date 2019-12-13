package com.godslew.cripple.presenter.timeline

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godslew.cripple.databinding.TimelineFragmentBinding
import com.godslew.cripple.domain.entity.Account
import com.godslew.cripple.domain.entity.TimelinePage
import com.godslew.cripple.domain.value.PageType
import com.godslew.cripple.presenter.BaseFragment


class TimelineFragment : BaseFragment() {

  companion object {
    fun newInstance() = TimelineFragment()
  }

  private lateinit var viewModel: TimelineViewModel
  private lateinit var binding: TimelineFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = TimelineFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(TimelineViewModel::class.java)
    // TODO: Use the ViewModel
    with(binding) {
      pager.adapter = TimelineAdapter(
        requireActivity(), listOf(
          TimelinePage(PageType.HOME, Account.initialize()),
          TimelinePage(PageType.MENTION, Account.initialize()),
          TimelinePage(PageType.LIKE, Account.initialize())
        )
      )
      // val skeletonScreen = Skeleton.bind(binding.pager)
      //   .shimmer(true)
      //   .angle(20)
      //   .duration(1200)
      //   .load(R.layout.item_skeleton_tweet)
      //   .show() //default count is 10
      // binding.pager.postDelayed({ skeletonScreen.hide() }, 3000)
    }
    return
  }

}
