package com.godslew.core.component.timeline

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.godslew.core.component.R
import com.godslew.core.component.databinding.ItemStatusBinding
import com.godslew.core.component.decoration.ImagesItemDecoration
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.core.java.value.MediaType
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem
import java.text.SimpleDateFormat
import java.util.*

data class ItemStatus(
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
          MediaType.PHOTO -> if (status.mediaEntities.size > 1) { ItemMedia.Photos(it) } else { ItemMedia.Photo(it) }
          MediaType.GIF -> ItemMedia.Gif(it)
          MediaType.VIDEO -> ItemMedia.Photo(it)
        }
      }.filter { it.mediaEntity.type != MediaType.VIDEO.type }
      images.visibility = if (items.isNotEmpty()) {
        View.VISIBLE
      } else {
        View.GONE
      }
      val spanSize = if (items.size > 1) { 2 } else { 1 }
      val currentAdapter = images.adapter
      if (currentAdapter != null && currentAdapter is GroupAdapter) {
        currentAdapter.clear()
        (images.layoutManager as? GridLayoutManager)?.spanCount = spanSize
        currentAdapter.updateAsync(items)
      } else {
        val adapter = GroupAdapter<GroupieViewHolder>()
        val manager = GridLayoutManager(root.context, spanSize)
        images.adapter = adapter
        images.layoutManager = manager
        images.addItemDecoration(ImagesItemDecoration.createDefaultDecoration(root.context))
        adapter.updateAsync(items)
      }
    }
  }

  override fun isSameAs(other: Item<*>?): Boolean {
    if (other != null && other is ItemStatus) {
      return other.crippleStatus.status.id == crippleStatus.status.id
    }
    return false
  }

  override fun equals(other: Any?): Boolean {
    if (other != null && other is ItemStatus) {
      return other.crippleStatus == crippleStatus
    }
    return false
  }

  override fun hashCode(): Int {
    return crippleStatus.hashCode()
  }
}
