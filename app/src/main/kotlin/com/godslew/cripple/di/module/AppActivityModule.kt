package com.godslew.cripple.di.module

import com.godslew.cripple.presenter.timeline.TimelineActivity
import com.godslew.cripple.presenter.tweet.TweetActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityModule {
  @ContributesAndroidInjector
  abstract fun contributeTimelineActivity(): TimelineActivity
}