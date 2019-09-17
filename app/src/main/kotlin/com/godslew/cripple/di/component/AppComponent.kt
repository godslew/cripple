package com.godslew.cripple.di.component

import com.godslew.cripple.di.module.AppModule
import com.godslew.cripple.di.module.SessionModule
import com.godslew.cripple.di.scope.AppScope
import com.godslew.cripple.presenter.timeline.TimelineActivity
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
  fun inject(activity: TimelineActivity)
  fun newSessionComponent(module: SessionModule) : SessionComponent
}