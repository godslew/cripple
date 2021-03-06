package com.godslew.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.godslew.core.android.app.AppRouterType
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.core.domain.store.AppStore
import com.godslew.core.java.entity.Account
import com.godslew.timeline.databinding.FragmentMentionBinding
import com.godslew.core.component.timeline.ItemStatus
import com.godslew.core.java.entity.CrippleStatus
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MentionFragment(
  private val account: Account
) : BaseFragment() {

  private lateinit var binding: FragmentMentionBinding

  private val disposables = CompositeDisposable()

  @Inject
  internal lateinit var appStore: AppStore
  @Inject
  internal lateinit var appRouter: AppRouterType
  @Inject
  internal lateinit var factory: ViewModelFactory<MentionViewModel>
  private val viewModel: MentionViewModel by viewModels { factory }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentMentionBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setup()
  }

  override fun onDestroyView() {
    disposables.dispose()
    super.onDestroyView()
  }

  private fun setup() {
    val imageClick : (status : CrippleStatus) -> Unit = {
      startActivity(appRouter.getImageDetailActivity(requireContext(), it))
    }
    setupRecyclerView()
    viewModel.fetchStatuses
      .bindTo {
        val currentAdapter = binding.list.adapter
        if (currentAdapter != null && currentAdapter is GroupAdapter) {
          currentAdapter.updateAsync(it.map { status ->
            ItemStatus(
              status,
              imageClick
            )
          })
        }

      }.addTo(disposables)

    viewModel.isFetching
      .bindTo {
        binding.refreshLayout.isRefreshing = it
      }.addTo(disposables)

    viewModel.setup(account)
  }

  private fun setupRecyclerView() {
    binding.refreshLayout.apply {
      setColorSchemeResources(R.color.white, R.color.colorLine, R.color.colorPrimary)
      setOnRefreshListener { viewModel.fetchTop(account) }
    }
    val adapter = GroupAdapter<GroupieViewHolder>()
    val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    binding.list.adapter = adapter
    binding.list.layoutManager = manager
    binding.list.scrollEvents()
      .filter { it.dy() > 0 }
      .filter { manager.itemCount != 0 }
      .filter { manager.findLastVisibleItemPosition() == manager.itemCount - 1 }
      .throttleFirst(1, TimeUnit.SECONDS)
      .bindTo {
        viewModel.fetchBottom(account)
      }
      .addTo(disposables)
  }
}