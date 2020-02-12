package com.godslew.core.component.image

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.godslew.core.component.R
import com.godslew.core.component.databinding.ItemImageDetailBinding
import com.xwray.groupie.databinding.BindableItem
import twitter4j.MediaEntity

class ItemImageDetail(
  private val media : MediaEntity
) : BindableItem<ItemImageDetailBinding>() {
  override fun getLayout(): Int = R.layout.item_image_detail

  override fun bind(viewBinding: ItemImageDetailBinding, position: Int) {
    with(viewBinding) {
      Glide.with(root.context)
        .asBitmap()
        .load(media.mediaURLHttps)
        .transform(FitCenter())
        .into(image)
    }
  }

}