package com.godslew.image.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.godslew.core.android.app.AppRouterType
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.core.component.image.ItemImageDetail
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.image.detail.databinding.FragmentImageDetailBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ImageDetailFragment(
  private val status : CrippleStatus
) : BaseFragment() {
  private val disposable = CompositeDisposable()

  @Inject
  internal lateinit var appRouter: AppRouterType

  @Inject
  internal lateinit var factory: ViewModelFactory<ImageDetailViewModel>
  private val viewModel: ImageDetailViewModel by activityViewModels { factory }
  private lateinit var binding: FragmentImageDetailBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentImageDetailBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroy() {
    disposable.dispose()
    super.onDestroy()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setup()
  }

  private fun setup() {
    with(binding) {
      val items = status.status.mediaEntities.map {
        ItemImageDetail(it)
      }
      val currentAdapter = images.adapter
      if (currentAdapter != null && currentAdapter is GroupAdapter) {
        currentAdapter.updateAsync(items)
      } else {
        val adapter = GroupAdapter<GroupieViewHolder>()
        images.adapter = adapter
        adapter.updateAsync(items)
      }
    }

  }

}