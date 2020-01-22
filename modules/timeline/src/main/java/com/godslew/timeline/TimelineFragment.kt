package com.godslew.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.godslew.core.android.app.AppRouterType
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.timeline.databinding.TimelineFragmentBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


class TimelineFragment : BaseFragment() {

  @Inject
  internal lateinit var appRouter: AppRouterType
  @Inject
  internal lateinit var factory: ViewModelFactory<TimelineViewModel>
  private val viewModel: TimelineViewModel by viewModels { factory }

  private val disposable = CompositeDisposable()
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

    // val skeletonScreen = Skeleton.bind(binding.pager)
    //   .shimmer(true)
    //   .angle(20)
    //   .duration(1200)
    //   .load(R.layout.item_skeleton_tweet)
    //   .show() //default count is 10
    // binding.pager.postDelayed({ skeletonScreen.hide() }, 3000)
    setup()
    return
  }

  private fun setup() {
    viewModel.loadTimeline
      .bindTo {
        val currentAdapter = binding.pager.adapter
        if (currentAdapter != null && currentAdapter is TimelineAdapter) {
          if (currentAdapter.itemCount != it.size) {
            binding.pager.adapter = TimelineAdapter(
              requireActivity(), it
            )
          }
        } else {
          binding.pager.adapter = TimelineAdapter(
            requireActivity(), it
          )
        }
      }.addTo(disposable)

    viewModel.setup()
  }

  override fun onResume() {
    super.onResume()
  }
}
