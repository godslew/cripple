package com.godslew.cripple.di.component

import com.godslew.cripple.di.module.SessionActivityModule
import com.godslew.cripple.di.module.SessionFragmentModule
import com.godslew.cripple.di.module.SessionModule
import com.godslew.core.android.scope.SessionScope
import dagger.Subcomponent
import dagger.android.DispatchingAndroidInjector

@SessionScope
@Subcomponent(modules = [
  SessionModule::class,
  SessionActivityModule::class,
  SessionFragmentModule::class])
interface SessionComponent {
  @Subcomponent.Builder
  interface Builder {
    fun sessionModule(sessionModule: SessionModule): Builder
    fun build(): SessionComponent
  }

  fun androidInjector(): DispatchingAndroidInjector<Any>
}
