package com.godslew.cripple.di.module

import com.godslew.tweet.TweetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SessionFragmentModule {
  @ContributesAndroidInjector
  abstract fun contributeTweetFragment() : TweetFragment

}