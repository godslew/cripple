package com.godslew.oauth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.oauth.databinding.ActivityTwitterLoginBinding

class TwitterLoginActivity : BaseActivity() {
  companion object {
    fun createIntent(context: Context) : Intent {
      return Intent(context, TwitterLoginActivity::class.java)
    }
  }

  private val binding by lazy { ActivityTwitterLoginBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
  }
}