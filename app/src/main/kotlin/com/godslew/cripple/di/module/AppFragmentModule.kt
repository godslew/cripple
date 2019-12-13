package com.godslew.cripple.di.module

import com.godslew.cripple.presenter.timeline.HomeFragment
import com.godslew.cripple.presenter.timeline.TimelineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppFragmentModule {
  @ContributesAndroidInjector
  abstract fun contributeTimelineFragment() : TimelineFragment

  @ContributesAndroidInjector
  abstract fun contributeHomeFragment() : HomeFragment
}