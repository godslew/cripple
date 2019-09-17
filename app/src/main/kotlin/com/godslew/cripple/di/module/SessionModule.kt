package com.godslew.cripple.di.module

import com.godslew.cripple.di.scope.SessionScope
import com.godslew.cripple.domain.entity.Account
import dagger.Module
import dagger.Provides
import twitter4j.Twitter
import twitter4j.TwitterFactory



@Module
class SessionModule(
  private val account: Account) {

  @SessionScope
  @Provides
  fun provideTwitter() : Twitter {
    val factory = TwitterFactory()
    val twitter = factory.instance
    twitter.setOAuthConsumer(account.consumerKey, account.consumerSecret)
    twitter.oAuthAccessToken = account.accessToken
    return twitter
  }
}