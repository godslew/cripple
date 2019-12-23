package com.godslew.cripple.di.module

import com.godslew.tweet.TweetActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SessionActivityModule {
  @ContributesAndroidInjector
  abstract fun contributeTweetActivity(): TweetActivity
}