package com.godslew.timeline.creator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.core.component.timeline.ItemTimelineTab
import com.godslew.core.component.timeline.TimelineTab
import com.godslew.core.java.entity.Account
import com.godslew.core.java.value.PageType
import com.godslew.timeline.creator.databinding.FragmentTimelineCreatorBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import javax.inject.Inject

class TimelineCreatorFragment(account: Account) : BaseFragment() {
  private lateinit var binding: FragmentTimelineCreatorBinding

  @Inject
  internal lateinit var factory: ViewModelFactory<TimelineCreatorViewModel>
  private val viewModel: TimelineCreatorViewModel by activityViewModels { factory }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    binding = FragmentTimelineCreatorBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setup()
  }

  private fun  setup() {
    val currentAdapter = binding.list.adapter
    val tabItems = PageType.getPageTypes().map { ItemTimelineTab(true, TimelineTab.getTimelineTabResource(it)) }
    if (currentAdapter != null && currentAdapter is GroupAdapter) {
      currentAdapter.updateAsync(tabItems)
    }
    val adapter = GroupAdapter<GroupieViewHolder>()
    val manager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
    binding.list.adapter = adapter
    binding.list.layoutManager = manager
    adapter.updateAsync(tabItems)
  }
}