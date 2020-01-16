package com.godslew.core.domain.usecase

import com.godslew.core.android.scope.SessionScope
import com.godslew.core.domain.repository.TwitterRepository
import com.godslew.core.java.entity.CrippleStatus
import io.reactivex.Single
import twitter4j.Twitter
import twitter4j.User
import javax.inject.Inject

@SessionScope
class TwitterUseCase @Inject constructor(
  private val twitter : Twitter,
  private val twitterRepository: TwitterRepository
) {
  fun updateStatus(text : String) : Single<CrippleStatus> {
    return twitterRepository.updateStatus(twitter, text)
  }

  fun verifyCredentials() : Single<User> {
    return twitterRepository.verifyCredentials(twitter)
  }
}