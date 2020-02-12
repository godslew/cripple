package com.godslew.cripple.di.module

import com.godslew.cripple.presenter.MainActivity
import com.godslew.image.detail.ImageDetailActivity
import com.godslew.oauth.TwitterLoginActivity
import com.godslew.timeline.creator.TimelineCreatorActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityModule {
  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity

  @ContributesAndroidInjector
  abstract fun contributeTwitterLoginActivity(): TwitterLoginActivity

  @ContributesAndroidInjector
  abstract fun contributeTimelineCreatorctivity(): TimelineCreatorActivity

  @ContributesAndroidInjector
  abstract fun contributeImageDetailActivity(): ImageDetailActivity
}