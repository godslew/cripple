package com.godslew.core.domain.usecase

import com.godslew.core.android.scope.SessionScope
import com.godslew.core.java.entity.CrippleStatus
import io.reactivex.Single
import twitter4j.Twitter
import twitter4j.TwitterException
import javax.inject.Inject

@SessionScope
class TwitterUseCase @Inject constructor(
  private val twitter : Twitter
) {
  fun postTweet(text : String) : Single<CrippleStatus> {
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
}