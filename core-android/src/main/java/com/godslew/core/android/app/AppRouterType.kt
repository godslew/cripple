package com.godslew.core.android.app

import android.content.Context
import android.content.Intent
import com.godslew.core.java.entity.Account
import com.godslew.core.java.entity.CrippleStatus

interface AppRouterType {
  fun getTweetActivity(
    context: Context
  ): Intent

  fun getOAuthActivity(
    context: Context
  ): Intent

  fun getTimelineCreatorActivity(
    context: Context,
    account: Account
  ): Intent

  fun getImageDetailActivity(
    context: Context,
    status: CrippleStatus
  ): Intent
}