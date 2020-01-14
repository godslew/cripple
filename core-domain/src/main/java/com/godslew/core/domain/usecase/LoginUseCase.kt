package com.godslew.core.domain.usecase

import android.content.Context
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.entity.Accounts
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.android.scope.AppScope
import com.godslew.core.domain.R
import com.godslew.gksettingpreferences.SettingPreferences
import io.reactivex.Single
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.TwitterFactory
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
        it.onSuccess(false)
        return@create
      }
      it.onSuccess(accounts.list.isNotEmpty())
      AppDispatcher.dispatch(AppAction.AccountAction.Load(accounts.list))
    }
  }

  companion object {
    private const val AccountsStoreName = "cripple_accounts"
  }

  // 認証用のインスタンスを生成
  fun createTwitterClientInstance(context: Context): Twitter {
    val consumerKey = context.getString(R.string.CK)
    val consumerSecret = context.getString(R.string.CS)

    val factory = TwitterFactory()
    val twitter = factory.instance
    twitter.setOAuthConsumer(consumerKey, consumerSecret)
    twitter.oAuthAccessToken = null
    return twitter
  }

  fun startAuthorize(tw : Twitter, callBack : String) : Single<RequestToken> {
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

  fun afterAuthorize(tw : Twitter, token : RequestToken, verifier : String) : Single<AccessToken> {
    return Single.create<AccessToken> {
      try {
        it.onSuccess(tw.getOAuthAccessToken(token, verifier))
      } catch (e: TwitterException) {
        it.onError(e)
        e.printStackTrace()
      }
    }
  }
}