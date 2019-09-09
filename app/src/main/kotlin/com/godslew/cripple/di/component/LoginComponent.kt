package com.godslew.cripple.di.component

import com.godslew.cripple.di.module.UseCaseModule
import com.godslew.cripple.presenter.timeline.TimelineActivity
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface LoginComponent {
  fun inject(activity: TimelineActivity)
}