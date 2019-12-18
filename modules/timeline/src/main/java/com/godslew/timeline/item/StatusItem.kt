package com.godslew.timeline.item

import com.godslew.timeline.R
import com.godslew.timeline.databinding.ItemStatusTypeTweetBinding
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem

sealed class StatusItem {
  data class TweetItem(
    //private val crippleStatus : CrippleStatus
    private val handleName : String
  ) : BindableItem<ItemStatusTypeTweetBinding>() {
    override fun getLayout(): Int = R.layout.item_status_type_tweet
    override fun bind(binding: ItemStatusTypeTweetBinding, position: Int) {
      with(binding) {
        //val status = crippleStatus.status
        name.text = handleName
        screenName.text = handleName//"@${status.user.screenName}"
        statusText.text = handleName //status.text
      }
    }

    override fun isSameAs(other: Item<*>?): Boolean {
      if (other != null && other is TweetItem) {
        return other.handleName == handleName
      }
      return false
    }

    override fun equals(other: Any?): Boolean {
      if (other != null && other is TweetItem) {
        return other.handleName == handleName
      }
      return false
    }

    override fun hashCode(): Int {
      return handleName.hashCode()
    }
  }
}