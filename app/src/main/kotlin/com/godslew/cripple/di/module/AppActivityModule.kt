package com.godslew.cripple.di.module

import com.godslew.cripple.presenter.timeline.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityModule {
  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity
}