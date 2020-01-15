package com.godslew.cripple.di.module

import com.godslew.core.android.scope.SessionScope
import com.godslew.core.android.utils.TwitterUtils
import com.godslew.core.java.entity.Account
import dagger.Module
import dagger.Provides
import twitter4j.Twitter


@Module
class SessionModule(
  private val account: Account
) {

  @SessionScope
  @Provides
  fun provideTwitter() : Twitter {
    return TwitterUtils.createTwitter(account)
  }
}