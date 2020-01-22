package com.godslew.timeline.item

import com.bumptech.glide.Glide
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.timeline.R
import com.godslew.timeline.databinding.ItemStatusTypeTweetBinding
import com.xwray.groupie.Item
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
        screenName.text = "@" + status.user.screenName
        statusText.text = status.text
        Glide.with(root.context)
          .asBitmap()
          .load(status.user.profileImageURLHttps)
          .centerCrop()
          .into(icon)

      }
    }

    override fun isSameAs(other: Item<*>?): Boolean {
      if (other != null && other is TweetItem) {
        return other.crippleStatus.status.id == crippleStatus.status.id
      }
      return false
    }

    override fun equals(other: Any?): Boolean {
      if (other != null && other is TweetItem) {
        return other.crippleStatus == crippleStatus
      }
      return false
    }

    override fun hashCode(): Int {
      return crippleStatus.hashCode()
    }
  }
}