package com.godslew.core.domain.repository

import com.godslew.core.java.entity.CrippleStatus
import io.reactivex.Single
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.User
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken
import javax.inject.Inject

class TwitterRepository @Inject constructor() {
  fun updateStatus(twitter : Twitter, text : String) : Single<CrippleStatus> {
    return Single.create {
      try {
        val status = twitter.updateStatus(text)
        it.onSuccess(CrippleStatus(status, status.user.id))
      } catch (e : TwitterException) {
        it.onError(e)
        e.printStackTrace()
      }
    }
  }

  fun verifyCredentials(twitter : Twitter) : Single<User> {
    return Single.create {
      try {
        val user = twitter.verifyCredentials()
        it.onSuccess(user)
      } catch (e : TwitterException) {
        it.onError(e)
        e.printStackTrace()
      }
    }
  }

  fun getRequestToken(twitter: Twitter, callBack : String) : Single<RequestToken> {
    return Single.create<RequestToken> {
      try {
        val requestToken = twitter.getOAuthRequestToken(callBack)
        it.onSuccess(requestToken)
      } catch (e: TwitterException) {
        it.onError(e)
        e.printStackTrace()
      }
    }
  }

  fun getAccessToken(twitter: Twitter, requestToken : RequestToken, verifier : String) : Single<AccessToken> {
    return Single.create<AccessToken> {
      try {
        val token = twitter.getOAuthAccessToken(requestToken, verifier)
        it.onSuccess(token)
      } catch (e: TwitterException) {
        it.onError(e)
        e.printStackTrace()
      }
    }
  }
}
