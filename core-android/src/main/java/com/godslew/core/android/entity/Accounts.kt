package com.godslew.core.android.entity

import com.godslew.core.java.entity.Account
import com.godslew.gksettingpreferences.SettingDataType

data class Accounts(
  val list : List<Account>
) : SettingDataType {

  companion object {
    fun initialize() : Accounts {
      return Accounts(list = mutableListOf())
    }
  }

}