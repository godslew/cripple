package com.godslew.cripple.di.module

import com.godslew.cripple.presenter.timeline.TimelineFragment
import com.godslew.cripple.presenter.tweet.TweetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SessionFragmentModule {
  @ContributesAndroidInjector
  abstract fun contributeTweetFragment() : TweetFragment

}