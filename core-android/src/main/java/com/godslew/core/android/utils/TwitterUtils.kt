package com.godslew.core.android.utils

import android.content.Context
import com.godslew.core.android.R
import com.godslew.core.java.entity.Account
import twitter4j.Twitter
import twitter4j.TwitterFactory

object TwitterUtils {
  // 認証用のインスタンスを生成
  fun createTwitterClientInstance(context: Context): Twitter {
    val keyPair = clientKey(context)
    val (consumerKey, consumerSecret) = keyPair

    val factory = TwitterFactory()
    val twitter = factory.instance
    twitter.setOAuthConsumer(consumerKey, consumerSecret)
    twitter.oAuthAccessToken = null
    return twitter
  }

  // 認証用のインスタンスを生成
  fun createTwitterInstance(context: Context, account : Account): Twitter {
    val twitter = createTwitterClientInstance(context)
    twitter.oAuthAccessToken = account.accessToken
    return twitter
  }

  fun clientKey(context: Context) : Pair<String, String> {
    return context.getString(R.string.CK) to context.getString(R.string.CS)
  }
}