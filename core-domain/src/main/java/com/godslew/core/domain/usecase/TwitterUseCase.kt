package com.godslew.core.domain.usecase

import com.godslew.core.android.scope.SessionScope
import com.godslew.core.java.entity.CrippleStatus
import io.reactivex.Single
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.User
import javax.inject.Inject

@SessionScope
class TwitterUseCase @Inject constructor(
  private val twitter : Twitter
) {
  fun updateStatus(text : String) : Single<CrippleStatus> {
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

  fun verifyCredentials() : Single<User> {
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
}