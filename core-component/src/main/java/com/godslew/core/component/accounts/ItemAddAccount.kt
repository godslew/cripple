package com.godslew.core.component.accounts

import com.godslew.core.component.R
import com.godslew.core.component.databinding.ItemAddAccountBinding
import com.xwray.groupie.databinding.BindableItem

class ItemAddAccount(
  val addAccount : () -> Unit
) : BindableItem<ItemAddAccountBinding>()  {
  override fun getLayout(): Int = R.layout.item_add_account

  override fun bind(viewBinding: ItemAddAccountBinding, position: Int) {
    viewBinding.root.setOnClickListener {
      addAccount()
    }
  }
}