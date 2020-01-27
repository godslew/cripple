package com.godslew.core.java.value

enum class PageType {
  HOME,
  MENTION,
  LIKE,
  DM,
  SEARCH,
  USER,
  LIST;

  companion object {
   fun getPageTypes() : List<PageType> {
     return listOf(HOME, MENTION, LIKE, DM, SEARCH, USER, LIST)
   }
  }
}