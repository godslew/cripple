package com.godslew.core.component.timeline

import com.godslew.core.component.R
import com.godslew.core.component.databinding.ItemTabTimelineBinding
import com.godslew.core.java.value.PageType
import com.xwray.groupie.databinding.BindableItem

data class ItemTimelineTab(
  val isEnable: Boolean,
  val tab : TimelineTab
) : BindableItem<ItemTabTimelineBinding>() {
  override fun getLayout(): Int = R.layout.item_tab_timeline

  override fun bind(viewBinding: ItemTabTimelineBinding, position: Int) {
    with(viewBinding) {
      if (isEnable) {
        root.setOnClickListener { }
      }
      icon.background = root.context.getDrawable(tab.iconSource)
      name.text = root.context.getString(tab.textSource)
    }
  }
}

sealed class TimelineTab {
  abstract val iconSource : Int
  abstract val textSource : Int
  data class Home(override val iconSource: Int = R.drawable.ic_home_white_24dp, override val textSource: Int = R.string.timeline_home ) : TimelineTab()
  data class Mention(override val iconSource: Int = R.drawable.ic_message_white_24dp, override val textSource: Int = R.string.timeline_mention ) : TimelineTab()
  data class Like(override val iconSource: Int = R.drawable.ic_favorite_white_24dp, override val textSource: Int = R.string.timeline_like ) : TimelineTab()
  data class DM(override val iconSource: Int = R.drawable.ic_mail_outline_white_24dp, override val textSource: Int = R.string.timeline_dm ) : TimelineTab()
  data class Search(override val iconSource: Int = R.drawable.ic_search_white_24dp, override val textSource: Int = R.string.timeline_search ) : TimelineTab()
  data class User(override val iconSource: Int = R.drawable.ic_account_circle_white_24dp, override val textSource: Int = R.string.timeline_user ) : TimelineTab()
  data class List(override val iconSource: Int = R.drawable.ic_list_white_24dp, override val textSource: Int = R.string.timeline_list ) : TimelineTab()

  companion object {
    fun getTimelineTabResource(pageType: PageType) : TimelineTab {
      return when(pageType) {
        PageType.HOME -> Home()
        PageType.MENTION -> Mention()
        PageType.LIKE -> Like()
        PageType.DM -> DM()
        PageType.SEARCH -> Search()
        PageType.USER -> User()
        PageType.LIST -> List()
      }
    }
  }
}