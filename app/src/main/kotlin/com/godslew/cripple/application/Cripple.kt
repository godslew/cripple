package com.godslew.cripple.application

import com.godslew.core.domain.store.AppStore
import com.godslew.cripple.BuildConfig
import com.godslew.cripple.di.component.AppComponent
import com.godslew.cripple.di.component.DaggerAppComponent
import com.godslew.cripple.di.component.SessionComponent
import com.godslew.cripple.di.module.AppModule
import com.godslew.cripple.di.module.SessionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class Cripple : DaggerApplication() {
  @Inject
  internal lateinit var appComponent: AppComponent

  @Inject
  internal lateinit var appStore: AppStore

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().appModule(AppModule(this)).build()
  }

  override fun androidInjector(): AndroidInjector<Any> {
    return findSessionComponent().androidInjector()
  }

  private fun findSessionComponent(): SessionComponent {
    val account = appStore.getState().current
    return appComponent.newSessionComponentBuilder()
      .sessionModule(SessionModule(account))
      .build()
  }

  override fun onCreate() {
    super.onCreate()
    initialize()
  }

  private fun initialize() {
    initializeTimber()
  }

  private fun initializeTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}