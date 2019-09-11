package com.godslew.cripple.presenter.timeline

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ethanhua.skeleton.Skeleton
import com.godslew.cripple.R
import com.godslew.cripple.databinding.TimelineFragmentBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder


class TimelineFragment : Fragment() {

  companion object {
    fun newInstance() = TimelineFragment()
  }

  private lateinit var viewModel: TimelineViewModel
  private lateinit var binding: TimelineFragmentBinding
  private lateinit var adapter : GroupAdapter<ViewHolder>

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = TimelineFragmentBinding.inflate(inflater, container, false)
    adapter = GroupAdapter()
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(TimelineViewModel::class.java)
    // TODO: Use the ViewModel
    binding.pager.adapter = adapter
    val skeletonScreen = Skeleton.bind(binding.pager)
      .shimmer(true)
      .angle(20)
      .duration(1200)
      .load(R.layout.item_skeleton_tweet)
      .show() //default count is 10
    binding.pager.postDelayed({ skeletonScreen.hide() }, 3000)
    return
  }

}
