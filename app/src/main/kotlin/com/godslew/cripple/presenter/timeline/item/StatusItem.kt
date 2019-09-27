package com.godslew.cripple.presenter.timeline.item

import com.godslew.cripple.R
import com.godslew.cripple.databinding.ItemStatusTypeTweetBinding
import com.godslew.cripple.domain.entity.CrippleStatus
import com.xwray.groupie.databinding.BindableItem

sealed class StatusItem {
  data class TweetItem(
    private val crippleStatus : CrippleStatus
  ) : BindableItem<ItemStatusTypeTweetBinding>() {
    override fun getLayout(): Int = R.layout.item_status_type_tweet
    override fun bind(binding: ItemStatusTypeTweetBinding, position: Int) {
      with(binding) {
        val status = crippleStatus.status
        name.text = status.user.name
        screenName.text = "@${status.user.screenName}"
        statusText.text = status.text
      }
    }
  }
}