package com.godslew.cripple.di.module

import com.godslew.account.AccountFragment
import com.godslew.image.detail.ImageDetailFragment
import com.godslew.timeline.HomeFragment
import com.godslew.timeline.MentionFragment
import com.godslew.timeline.TimelineFragment
import com.godslew.timeline.creator.TimelineCreatorFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppFragmentModule {
  @ContributesAndroidInjector
  abstract fun contributeTimelineFragment() : TimelineFragment

  @ContributesAndroidInjector
  abstract fun contributeHomeFragment() : HomeFragment

  @ContributesAndroidInjector
  abstract fun contributeMentionFragment() : MentionFragment

  @ContributesAndroidInjector
  abstract fun contributeAccountFragment() : AccountFragment

  @ContributesAndroidInjector
  abstract fun contributeTimelineCreatorFragment() : TimelineCreatorFragment

  @ContributesAndroidInjector
  abstract fun contributeImageDetailFragment() : ImageDetailFragment
}