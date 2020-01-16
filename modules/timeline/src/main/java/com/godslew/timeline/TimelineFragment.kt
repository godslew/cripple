package com.godslew.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.app.AppRouterType
import com.godslew.core.android.entity.TimelinePage
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.java.entity.Account
import com.godslew.core.java.value.PageType
import com.godslew.timeline.databinding.TimelineFragmentBinding
import javax.inject.Inject


class TimelineFragment : BaseFragment() {

  @Inject
  internal lateinit var appRouter: AppRouterType
  @Inject internal lateinit var factory: ViewModelFactory<TimelineViewModel>
  private val viewModel: TimelineViewModel by viewModels { factory }

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
    AppDispatcher.dispatch(AppAction.AppViewAction.MainViewAction.DisplayTweetButtonAction(true))
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

  override fun onResume() {
    AppDispatcher.dispatch(AppAction.AppViewAction.MainViewAction.DisplayTweetButtonAction(true))
    super.onResume()
  }

}
