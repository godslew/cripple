package com.godslew.cripple.di.component

import com.godslew.cripple.application.Cripple
import com.godslew.cripple.di.module.AppActivityModule
import com.godslew.cripple.di.module.AppFragmentModule
import com.godslew.cripple.di.module.AppModule
import com.godslew.cripple.di.module.SessionModule
import com.godslew.cripple.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [
  AndroidSupportInjectionModule::class,
  AppModule::class,
  AppActivityModule::class,
  AppFragmentModule::class])
interface AppComponent : AndroidInjector<Cripple> {
  @Component.Builder
  interface Builder {
    fun appModule(appModule: AppModule): Builder
    fun build(): AppComponent
  }
  fun newSessionComponentBuilder(): SessionComponent.Builder
}