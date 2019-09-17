package com.godslew.cripple.application

import android.app.Application
import com.godslew.cripple.di.component.AppComponent
import com.godslew.cripple.di.component.DaggerAppComponent
import com.godslew.cripple.di.module.AppModule

class Cripple : Application() {

 private lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    initializeDagger()
  }

  private fun initializeDagger() {
    appComponent = DaggerAppComponent.builder()
      .appModule(AppModule())
      .build()
  }

  fun getAppComponent(): AppComponent {
    return appComponent
  }
}