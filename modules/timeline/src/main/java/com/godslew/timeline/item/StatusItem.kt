package com.godslew.timeline.item

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.godslew.core.component.timeline.ItemMedia
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.core.java.value.MediaType
import com.godslew.timeline.R
import com.godslew.timeline.databinding.ItemStatusBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem
import java.text.SimpleDateFormat
import java.util.*

data class StatusItem(
  private val crippleStatus: CrippleStatus
) : BindableItem<ItemStatusBinding>() {
  override fun getLayout(): Int = R.layout.item_status
  override fun bind(binding: ItemStatusBinding, position: Int) {
    with(binding) {
      val status = if (crippleStatus.status.isRetweet) {
        crippleStatus.status.retweetedStatus
      } else {
        crippleStatus.status
      }
      name.text = status.user.name
      screenName.text = "@" + status.user.screenName
      statusText.text = status.text
      date.text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.JAPAN).format(status.createdAt)
      Glide.with(root.context)
        .asBitmap()
        .load(status.user.profileImageURLHttps)
        .centerCrop()
        .into(icon)

      val items = status.mediaEntities.map {
        when (MediaType.getType(it.type)) {
          MediaType.PHOTO -> ItemMedia.Photo(it)
          MediaType.GIF -> ItemMedia.Photo(it)
          MediaType.VIDEO -> ItemMedia.Photo(it)
        }
      }.filter { it.mediaEntity.type == MediaType.PHOTO.type }
      images.visibility = if (items.isNotEmpty()) {
        View.VISIBLE
      } else {
        View.GONE
      }
      val currentAdapter = images.adapter
      if (currentAdapter != null && currentAdapter is GroupAdapter) {
        currentAdapter.updateAsync(items)
      } else {
        val adapter = GroupAdapter<GroupieViewHolder>()
        val manager = GridLayoutManager(root.context, 2, LinearLayoutManager.VERTICAL, false)
        images.adapter = adapter
        images.layoutManager = manager
        adapter.updateAsync(items)
      }
    }
  }

  override fun isSameAs(other: Item<*>?): Boolean {
    if (other != null && other is StatusItem) {
      return other.crippleStatus.status.id == crippleStatus.status.id
    }
    return false
  }

  override fun equals(other: Any?): Boolean {
    if (other != null && other is StatusItem) {
      return other.crippleStatus == crippleStatus
    }
    return false
  }

  override fun hashCode(): Int {
    return crippleStatus.hashCode()
  }
}
