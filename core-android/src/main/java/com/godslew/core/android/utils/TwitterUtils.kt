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
    return createTwitterInstance(consumerKey, consumerSecret)
  }

  private fun createTwitterInstance(ck : String, cs : String) : Twitter {
    val factory = TwitterFactory()
    val twitter = factory.instance
    twitter.setOAuthConsumer(ck, cs)
    twitter.oAuthAccessToken = null
    return twitter
  }

  // APIアクセス用のインスタンスを生成
  fun createTwitter(account : Account): Twitter {
    val twitter = createTwitterInstance(account.consumerKey, account.consumerSecret)
    twitter.oAuthAccessToken = account.accessToken
    return twitter
  }

  fun clientKey(context: Context) : Pair<String, String> {
    return context.getString(R.string.CK) to context.getString(R.string.CS)
  }
}