package com.godslew.cripple.presenter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.redux.AppReducer
import com.godslew.core.android.state.AppState
import com.godslew.core.android.store.AppStore
import com.godslew.core.domain.usecase.LoginUseCase
import com.godslew.core.java.entity.Account
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
  application: Application,
  private val appStore: AppStore<AppState, AppAction, AppReducer>,
  private val loginUseCase: LoginUseCase
) : AndroidViewModel(application) {

  private val disposable  = CompositeDisposable()

  val loadedAccounts : PublishRelay<List<Account>> = PublishRelay.create()
  val isShowTweetButton : PublishRelay<Boolean> = PublishRelay.create()


  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

  fun setup() {
    loginUseCase.loadAccounts()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .bindTo { loadedAccounts.accept(it) }
      .addTo(disposable)

    appStore
      .observable()
      .map { it.viewState.mainViewState.isShowTweetButton }
      .distinctUntilChanged()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .bindTo { isShowTweetButton.accept(it) }
      .addTo(disposable)
  }
}