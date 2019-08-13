package com.godslew.cripple.ui.tweet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.godslew.cripple.R

class TweetActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.tweet_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, TweetFragment.newInstance())
        .commitNow()
    }
  }

  companion object {
    fun createIntent(context: Context): Intent {
      return Intent(context, TweetActivity::class.java)
    }
  }

}
