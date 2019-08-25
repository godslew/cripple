package com.godslew.cripple.presenter.tweet

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class TweetViewModel : ViewModel() {
  private val disposables = CompositeDisposable()

  override fun onCleared() {
    disposables.dispose()
  }
  fun postTweet(context: Context, text: String) {
      Toast.makeText(context, text, Toast.LENGTH_LONG).show()
  }
}
