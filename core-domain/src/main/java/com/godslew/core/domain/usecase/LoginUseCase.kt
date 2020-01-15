package com.godslew.core.domain.usecase

import android.content.Context
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.entity.Accounts
import com.godslew.core.android.redux.AppDispatcher
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
  fun hasLoginSession(): Single<Boolean> {
    return Single.create {
      val accounts = settingPreferences.load(AccountsStoreName, Accounts::class.java)
      if (accounts == null) {
        AppDispatcher.dispatch(AppAction.AccountAction.Load(Accounts.initialize().list))
        it.onSuccess(false)
        return@create
      }
      it.onSuccess(accounts.list.isNotEmpty())
      AppDispatcher.dispatch(AppAction.AccountAction.Load(accounts.list))
    }
  }

  fun createAccount(context: Context, accessToken: AccessToken) : Account {
    val keyPair = TwitterUtils.clientKey(context)
    val (consumerKey, consumerSecret) = keyPair
    return Account(consumerKey, consumerSecret, accessToken)
  }

  companion object {
    private const val AccountsStoreName = "cripple_accounts"
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
}