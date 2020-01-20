package com.godslew.core.domain.repository

import com.godslew.core.android.entity.Accounts
import com.godslew.core.java.entity.Account
import com.godslew.gksettingpreferences.SettingPreferences
import io.reactivex.Single
import javax.inject.Inject

class AccountRepository @Inject constructor(
  private val settingPreferences: SettingPreferences
  ){


  fun getAccounts() : Single<List<Account>> {
    return Single.create {
      val accounts = settingPreferences.load(AccountsStoreName, Accounts::class.java)
      if (accounts == null) {
        it.onSuccess(mutableListOf())
        return@create
      }
      it.onSuccess(accounts.list)
    }
  }

  fun registerAccount(account: Account) : Single<List<Account>> {
    return Single.create { emitter ->
      // 登録済みのアカウントのリストを取得
      val accounts = settingPreferences.load(AccountsStoreName, Accounts::class.java)
      // 初回登録の場合
      if (accounts == null) {
        val list = listOf(account)
        emitter.onSuccess(list)
        return@create
      }
      val list = accounts.list
      // アカウントを全て消してしまった場合
      if (list.isEmpty()) {
        val l = listOf(account)
        emitter.onSuccess(l)
        return@create
      }
      // 未登録アカウントの場合
      if (list.firstOrNull{it.userId() == account.userId()} == null) {
        emitter.onSuccess(list.plus(account))
        return@create
      }
      // 登録済みのアカウントの場合
      emitter.onSuccess(list)
    }
  }

  fun saveAccount(accounts : List<Account>) {
    settingPreferences.save(AccountsStoreName, Accounts(accounts))
  }

  companion object {
    private const val AccountsStoreName = "cripple_accounts"
  }
}