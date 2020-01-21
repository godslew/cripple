package com.godslew.tweet

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.domain.store.AppStore
import com.godslew.core.domain.usecase.TwitterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TweetViewModel @Inject constructor(
  application: Application,
  appStore: AppStore,
  private val twitterUseCase: TwitterUseCase
) : AndroidViewModel(application) {
  private val disposables = CompositeDisposable()

  override fun onCleared() {
    disposables.dispose()
  }
  fun postTweet(context: Context, text: String) {
      Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    twitterUseCase.verifyCredentials()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo {
      }.addTo(disposables)
  }
}
