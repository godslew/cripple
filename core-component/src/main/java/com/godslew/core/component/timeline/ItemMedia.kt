package com.godslew.core.component.timeline

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.godslew.core.component.R
import com.godslew.core.component.databinding.ItemImageInStatusBinding
import com.godslew.core.component.databinding.ItemImagesInStatusBinding
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
          .transform(CenterCrop(), RoundedCorners(25))
          .into(photo)
      }
    }
  }

  data class Photos(
    override val mediaEntity: MediaEntity
  ) : ItemMedia<ItemImagesInStatusBinding>() {

    override fun getLayout(): Int = R.layout.item_images_in_status

    override fun bind(viewBinding: ItemImagesInStatusBinding, position: Int) {
      with(viewBinding) {
        Glide.with(root.context)
          .asBitmap()
          .load(mediaEntity.mediaURLHttps)
          .transform(CenterCrop(), RoundedCorners(25))
          .into(photo)
      }
    }
  }
  // TODO use ExoPlayer
  data class Gif(
    override val mediaEntity: MediaEntity
  ) : ItemMedia<ItemImageInStatusBinding>() {

    override fun getLayout(): Int = R.layout.item_image_in_status

    override fun bind(viewBinding: ItemImageInStatusBinding, position: Int) {
      with(viewBinding) {
        Glide.with(root.context)
          .asBitmap()
          .load(mediaEntity.mediaURLHttps)
          .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
          .into(photo)
      }
    }
  }
}

