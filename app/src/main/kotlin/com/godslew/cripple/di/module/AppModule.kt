package com.godslew.cripple.di.module

import com.godslew.cripple.di.scope.AppScope
import com.godslew.cripple.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule {
  @AppScope
  @Provides
  fun provideLoginUseCase(): LoginUseCase {
    return LoginUseCase()
  }

}