package com.godslew.cripple.di.module

import android.app.Application
import com.godslew.core.android.scope.AppScope
import com.godslew.core.android.store.AppStore
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.app.AppRouterType
import com.godslew.core.android.redux.AppReducer
import com.godslew.core.android.state.AppState
import com.godslew.cripple.application.AppRouter
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
  @Provides
  fun provideAppRouterType(): AppRouterType {
    return AppRouter()
  }
}
