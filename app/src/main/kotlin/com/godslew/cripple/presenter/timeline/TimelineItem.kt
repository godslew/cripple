package com.godslew.cripple.presenter.timeline

import androidx.recyclerview.widget.LinearLayoutManager
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ItemTimelineBinding
import com.godslew.cripple.domain.entity.CrippleStatus
import com.godslew.cripple.presenter.timeline.item.StatusItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class TimelineItem(
  private val tabType : String,
  private val statuses : List<CrippleStatus>
) : BindableItem<ItemTimelineBinding>() {
  private val disposables = CompositeDisposable()
  private val adapter = GroupAdapter<ViewHolder>()
  override fun getLayout(): Int = R.layout.item_timeline
  override fun bind(binding: ItemTimelineBinding, position: Int) {
    with(binding) {
      if (statusList.adapter !is GroupAdapter) {
        val manager = LinearLayoutManager(root.context)
        statusList.layoutManager = manager
        swipe.apply {
          setColorSchemeResources(R.color.white, R.color.colorAccent, R.color.light_transparent)
          setOnRefreshListener { refresh(binding) }
        }
        statusList.adapter = adapter
      }
      adapter.update(
        statuses.map {
          StatusItem.TweetItem(it)
        }
      )
    }
  }
  private fun refresh(binding: ItemTimelineBinding) {
    binding.swipe.isRefreshing = true
    Single.create(SingleOnSubscribe<Unit>{
      Thread.sleep(1000)
      it.onSuccess(Unit)
    }).subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSuccess {
        println("finish")
        binding.swipe.isRefreshing = false }
      .subscribe()
      .addTo(disposables)
  }
}