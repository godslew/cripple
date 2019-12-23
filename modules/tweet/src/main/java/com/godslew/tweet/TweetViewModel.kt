package com.godslew.tweet

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TweetViewModel @Inject constructor(
  application: Application
) : ViewModel() {
  private val disposables = CompositeDisposable()

  override fun onCleared() {
    disposables.dispose()
  }
  fun postTweet(context: Context, text: String) {
      Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
  }
}
