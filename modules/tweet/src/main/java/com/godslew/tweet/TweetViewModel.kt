package com.godslew.tweet

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.redux.AppReducer
import com.godslew.core.android.state.AppState
import com.godslew.core.android.store.AppStore
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TweetViewModel @Inject constructor(
  application: Application,
  appStore: AppStore<AppState, AppAction, AppReducer>
) : AndroidViewModel(application) {
  private val disposables = CompositeDisposable()

  override fun onCleared() {
    disposables.dispose()
  }
  fun postTweet(context: Context, text: String) {
      Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
  }
}
