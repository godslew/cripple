package com.godslew.core.component.accounts

import com.godslew.core.component.R
import com.godslew.core.component.databinding.ItemAccountBinding
import com.godslew.core.java.entity.Account
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem

class ItemAccount(
  val account: Account
) : BindableItem<ItemAccountBinding>()  {
  override fun getLayout(): Int = R.layout.item_account

  override fun bind(viewBinding: ItemAccountBinding, position: Int) {
    with(viewBinding) {
      this.screenName.text = account.screenname()
    }

  }

  override fun isSameAs(other: Item<*>?): Boolean {
    return if (other != null && other is ItemAccount) {
      other.account.userId() == account.userId()
    } else {
      false
    }
  }

  override fun equals(other: Any?): Boolean {
    return if (other != null && other is ItemAccount) {
      other.account == account
    } else {
      false
    }
  }

  override fun hashCode(): Int {
    return account.hashCode()
  }
}