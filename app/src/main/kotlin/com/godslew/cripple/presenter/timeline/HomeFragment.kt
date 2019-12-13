package com.godslew.cripple.presenter.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.godslew.cripple.databinding.FragmentHomeBinding
import com.godslew.cripple.presenter.BaseFragment
import com.godslew.cripple.presenter.timeline.item.StatusItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class HomeFragment : BaseFragment() {

  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecycleView()
  }

  override fun onDestroyView() {
    super.onDestroyView()
  }

  private fun setupRecycleView() {
    val adapter = GroupAdapter<ViewHolder>()
    val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    binding.list.adapter = adapter
    binding.list.layoutManager = manager

    adapter.update(mutableListOf(
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga"),
      StatusItem.TweetItem("FugaHuga")
    ))
  }
}