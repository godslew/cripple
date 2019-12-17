package com.godslew.core.domain.usecase

import javax.inject.Inject

class LoginUseCase @Inject constructor() {
  fun hasLoginSession(): Boolean {
    return false
  }
}