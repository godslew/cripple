package com.godslew.cripple.presenter.timeline

import androidx.recyclerview.widget.LinearLayoutManager
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ItemTimelineBinding
import com.godslew.cripple.domain.entity.CrippleStatus
import com.godslew.cripple.presenter.timeline.item.StatusItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem

class TimelineItem(
  private val tabType : String,
  private val statuses : List<CrippleStatus>
) : BindableItem<ItemTimelineBinding>() {
  private val adapter = GroupAdapter<ViewHolder>()
  override fun getLayout(): Int = R.layout.item_timeline
  override fun bind(binding: ItemTimelineBinding, position: Int) {
    with(binding) {
      if (statusList.adapter !is GroupAdapter) {
        val manager = LinearLayoutManager(root.context)
        statusList.layoutManager = manager
        statusList.adapter = adapter
      }
      adapter.update(
        statuses.map {
          StatusItem.TweetItem(it)
        }
      )
    }
  }
}