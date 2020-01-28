package com.godslew.core.java.value

enum class MediaType(val type : String) {
  PHOTO("photo"),
  GIF("animated_gif"),
  VIDEO("video");

  companion object {
    fun getType(type : String) : MediaType {
      return values().first { it.type == type }
    }
  }
}
