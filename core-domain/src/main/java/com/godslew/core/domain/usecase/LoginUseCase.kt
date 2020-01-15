package com.godslew.core.domain.usecase

import android.content.Context
import com.godslew.core.android.entity.Accounts
import com.godslew.core.android.scope.AppScope
import com.godslew.core.android.utils.TwitterUtils
import com.godslew.core.java.entity.Account
import com.godslew.gksettingpreferences.SettingPreferences
import io.reactivex.Single
import twitter4j.TwitterException
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken
import javax.inject.Inject

@AppScope
class LoginUseCase @Inject constructor(
  private val settingPreferences: SettingPreferences
) {

  companion object {
    private const val AccountsStoreName = "cripple_accounts"
  }

  fun loadAccounts(): Single<List<Account>> {
    return Single.create {
      val accounts = settingPreferences.load(AccountsStoreName, Accounts::class.java)
      if (accounts == null) {
        it.onSuccess(mutableListOf())
        return@create
      }
      it.onSuccess(accounts.list)
    }
  }

  fun createAccount(context: Context, accessToken: AccessToken) : Account {
    val keyPair = TwitterUtils.clientKey(context)
    val (consumerKey, consumerSecret) = keyPair
    return Account(consumerKey, consumerSecret, accessToken)
  }


  fun startAuthorize(context: Context, callBack : String) : Single<RequestToken> {
    val tw = TwitterUtils.createTwitterClientInstance(context)
    return Single.create<RequestToken> {
      try {
        val requestToken = tw.getOAuthRequestToken(callBack)
        it.onSuccess(requestToken)
      } catch (e: TwitterException) {
        it.onError(e)
        e.printStackTrace()
      }
    }
  }

  fun afterAuthorize(context: Context, requestToken : RequestToken, verifier : String) : Single<AccessToken> {
    val tw = TwitterUtils.createTwitterClientInstance(context)
    return Single.create<AccessToken> {
      try {
        val token = tw.getOAuthAccessToken(requestToken, verifier)
        it.onSuccess(token)
      } catch (e: TwitterException) {
        it.onError(e)
        e.printStackTrace()
      }
    }
  }

  fun registerAccount(account: Account) : Single<List<Account>> {
    return Single.create { emitter ->
      // 登録済みのアカウントのリストを取得
      val accounts = settingPreferences.load(AccountsStoreName, Accounts::class.java)
      // 初回登録の場合
      if (accounts == null) {
        val list = listOf(account)
        saveAccount(list)
        emitter.onSuccess(list)
        return@create
      }
      val list = accounts.list
      // アカウントを全て消してしまった場合
      if (list.isEmpty()) {
        val l = listOf(account)
        saveAccount(l)
        emitter.onSuccess(l)
        return@create
      }
      // 未登録登アカウントの場合
      if (list.firstOrNull{it.userId() == account.userId()} == null) {
        saveAccount(list)
        emitter.onSuccess(list.plus(account))
        return@create
      }
      // 登録済みのアカウントの場合
      emitter.onSuccess(list)
    }
  }

  private fun saveAccount(accounts: List<Account>) {
    settingPreferences.save(AccountsStoreName, Accounts(accounts))
  }
}