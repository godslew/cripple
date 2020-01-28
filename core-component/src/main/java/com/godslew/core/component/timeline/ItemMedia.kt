package com.godslew.core.component.timeline

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.godslew.core.component.R
import com.godslew.core.component.databinding.ItemImageInStatusBinding
import com.xwray.groupie.databinding.BindableItem
import twitter4j.MediaEntity

sealed class ItemMedia<T : ViewDataBinding> : BindableItem<T>() {
  abstract val mediaEntity: MediaEntity

  data class Photo(
    override val mediaEntity: MediaEntity
  ) : ItemMedia<ItemImageInStatusBinding>() {

    override fun getLayout(): Int = R.layout.item_image_in_status

    override fun bind(viewBinding: ItemImageInStatusBinding, position: Int) {
      with(viewBinding) {
        Glide.with(root.context)
          .asBitmap()
          .load(mediaEntity.mediaURLHttps)
          .centerCrop()
          .into(photo)
      }
    }
  }
}

