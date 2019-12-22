package com.godslew.cripple.di.module

import android.app.Application
import com.godslew.core.android.scope.AppScope
import com.godslew.gksettingpreferences.SettingPreferences
import dagger.Module
import dagger.Provides

@Module
class AppModule(
  private val application: Application
) {

  @AppScope
  @Provides
  fun provideApplication(): Application {
    return application
  }

  @AppScope
  @Provides
  fun provideSettingPreferences(application: Application): SettingPreferences {
    return SettingPreferences(application)
  }
}
