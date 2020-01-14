package com.godslew.cripple.di.module

import com.godslew.cripple.presenter.MainActivity
import com.godslew.oauth.TwitterLoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityModule {
  @ContributesAndroidInjector
  abstract fun contributeMainActivity(): MainActivity

  @ContributesAndroidInjector
  abstract fun contributeTwitterLoginActivity(): TwitterLoginActivity
}