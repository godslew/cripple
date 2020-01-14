package com.godslew.oauth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.godslew.core.android.presenter.BaseActivity

class TwitterLoginActivity : BaseActivity() {
  companion object {
    fun createIntent(context: Context) : Intent {
      return Intent(context, TwitterLoginActivity::class.java)
    }
  }
  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
  }
}