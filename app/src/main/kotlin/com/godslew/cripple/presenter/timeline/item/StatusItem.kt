package com.godslew.cripple.presenter.timeline.item

import com.godslew.cripple.R
import com.godslew.cripple.databinding.ItemStatusTypeTweetBinding
import com.godslew.cripple.domain.entity.CrippleStatus
import com.xwray.groupie.databinding.BindableItem

sealed class StatusItem {
  data class TweetItem(
    //private val crippleStatus : CrippleStatus
    private val handleName : String
  ) : BindableItem<ItemStatusTypeTweetBinding>() {
    override fun getLayout(): Int = R.layout.item_status_type_tweet
    override fun bind(binding: ItemStatusTypeTweetBinding, position: Int) {
      with(binding) {
        //val status = crippleStatus.status
        name.text = handleName
        screenName.text = handleName//"@${status.user.screenName}"
        statusText.text = handleName //status.text
      }
    }
  }
}