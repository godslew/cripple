package com.godslew.core.android.app

import android.content.Context
import android.content.Intent

interface AppRouterType {
  fun getTweetActivity(
    context: Context
  ) : Intent

  fun getOAuthActivity(
    context: Context
  ) : Intent
}