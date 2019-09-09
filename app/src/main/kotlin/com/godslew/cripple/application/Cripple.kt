package com.godslew.cripple.application

import android.app.Application
import com.godslew.cripple.di.component.DaggerLoginComponent
import com.godslew.cripple.di.component.LoginComponent
import com.godslew.cripple.di.module.UseCaseModule

class Cripple : Application() {

 private lateinit var loginComponent: LoginComponent

  override fun onCreate() {
    super.onCreate()
    initializeDagger()
  }

  private fun initializeDagger() {
    loginComponent = DaggerLoginComponent.builder()
      .useCaseModule(UseCaseModule())
      .build()
  }

  fun getLoginComponent(): LoginComponent {
    return loginComponent
  }
}