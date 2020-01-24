package com.godslew.timeline.creator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.core.java.entity.Account
import com.godslew.timeline.creator.databinding.FragmentTimelineCreatorBinding
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
}