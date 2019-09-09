package com.godslew.cripple.di.module

import com.godslew.cripple.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
  @Provides
  fun provideLoginUseCase(): LoginUseCase {
    return LoginUseCase()
  }

}