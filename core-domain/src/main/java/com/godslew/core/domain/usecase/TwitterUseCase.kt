package com.godslew.core.domain.usecase

import com.godslew.core.android.scope.AppScope
import com.godslew.core.android.utils.TwitterUtils
import com.godslew.core.domain.repository.TwitterRepository
import com.godslew.core.java.entity.Account
import com.godslew.core.java.entity.CrippleStatus
import io.reactivex.Single
import twitter4j.Paging
import twitter4j.Twitter
import twitter4j.User
import javax.inject.Inject

@AppScope
class TwitterUseCase @Inject constructor(
  private val twitterRepository: TwitterRepository
) {
  fun updateStatus(account: Account, text : String) : Single<CrippleStatus> {
    val tw = TwitterUtils.createTwitter(account)
    return twitterRepository.updateStatus(tw, text)
  }

  fun verifyCredentials(account: Account) : Single<User> {
    val tw = TwitterUtils.createTwitter(account)
    return twitterRepository.verifyCredentials(tw)
  }

  fun getHomeTimeline(account: Account) : Single<List<CrippleStatus>> {
    val tw = TwitterUtils.createTwitter(account)
    val paging = Paging()
    paging.count = FetchStatusLimit
    return twitterRepository.getHomeTimeline(tw, paging)
  }

  fun getHomeTimelineBySinceId(account: Account, sinceId : Long) : Single<List<CrippleStatus>> {
    val tw = TwitterUtils.createTwitter(account)
    val paging = Paging()
    paging.sinceId = sinceId
    paging.count = FetchStatusLimit
    return twitterRepository.getHomeTimeline(tw, paging)
  }

  fun getHomeTimelineByMaxId(account: Account, maxId : Long) : Single<List<CrippleStatus>> {
    val tw = TwitterUtils.createTwitter(account)
    val paging = Paging()
    paging.maxId = maxId
    paging.count = FetchStatusLimit
    return twitterRepository.getHomeTimeline(tw, paging)
  }

  companion object {
    private const val FetchStatusLimit = 50
  }
}