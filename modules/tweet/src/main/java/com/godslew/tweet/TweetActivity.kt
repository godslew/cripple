package com.godslew.tweet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.tweet.databinding.ActivityTweetBinding

class TweetActivity : BaseActivity() {

  private val  binding by lazy { ActivityTweetBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(binding.container.id, TweetFragment.newInstance())
        .commitNow()
    }
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
  }

  companion object {
    fun createIntent(context: Context): Intent {
      return Intent(context, TweetActivity::class.java)
    }
  }

}
