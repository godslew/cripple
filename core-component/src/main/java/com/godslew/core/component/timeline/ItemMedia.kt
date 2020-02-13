package com.godslew.core.component.timeline

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.godslew.core.component.R
import com.godslew.core.component.databinding.ItemImageInStatusBinding
import com.godslew.core.component.databinding.ItemImagesInStatusBinding
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem
import twitter4j.MediaEntity


sealed class ItemMedia<T : ViewDataBinding> : BindableItem<T>() {
  abstract val mediaEntity: MediaEntity

  override fun equals(other: Any?): Boolean {
    return if (other != null && other is ItemMedia<*>) {
      other.mediaEntity.id == mediaEntity.id
    } else {
      false
    }
  }

  override fun isSameAs(other: Item<*>?): Boolean {
    return if (other != null && other is ItemMedia<*>) {
      other.mediaEntity == mediaEntity
    } else {
      false
    }
  }

  override fun hashCode(): Int {
    return mediaEntity.hashCode()
  }

  data class Photo(
    override val mediaEntity: MediaEntity
  ) : ItemMedia<ItemImageInStatusBinding>() {

    override fun getLayout(): Int = R.layout.item_image_in_status

    override fun bind(viewBinding: ItemImageInStatusBinding, position: Int) {
      with(viewBinding) {
        Glide.with(root.context)
          .asBitmap()
          .load(mediaEntity.mediaURLHttps)
          .diskCacheStrategy(DiskCacheStrategy.ALL)
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
          .diskCacheStrategy(DiskCacheStrategy.ALL)
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
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
          .into(photo)
      }
    }
  }
}

