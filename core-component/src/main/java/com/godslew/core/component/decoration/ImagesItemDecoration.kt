package com.godslew.core.component.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.godslew.core.component.R


class ImagesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    outRect.top = space
  }

  companion object {
    fun createDefaultDecoration(context: Context): ImagesItemDecoration {
      val spacingInPixels: Int = context.resources.getDimensionPixelSize(R.dimen.spacing_xs)
      return ImagesItemDecoration(spacingInPixels)
    }
  }

}