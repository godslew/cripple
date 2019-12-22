package com.godslew.cripple.di.module

import android.app.Application
import com.godslew.core.android.scope.AppScope
import com.godslew.core.android.store.AppStore
import com.godslew.cripple.domain.action.AppAction
import com.godslew.cripple.domain.reducer.AppReducer
import com.godslew.cripple.domain.state.AppState
import com.godslew.gksettingpreferences.SettingPreferences
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

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

  @AppScope
  @Provides
  fun provideAppStore(): AppStore<AppState, AppAction, AppReducer> {
    return AppStore(
      disposable = CompositeDisposable(),
      initialState = AppState.initial(),
      reducer = AppReducer
    )
  }
}
