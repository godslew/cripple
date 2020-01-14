package com.godslew.oauth

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
import twitter4j.auth.RequestToken
import javax.inject.Inject

class TwitterLoginViewModel @Inject constructor(
  application: Application,
  private val appStore: AppStore<AppState, AppAction, AppReducer>,
  private val loginUseCase: LoginUseCase
) : AndroidViewModel(application) {

  private val disposable = CompositeDisposable()

  val requestToken: PublishRelay<RequestToken> = PublishRelay.create()
  val account : PublishRelay<Account> = PublishRelay.create()
  val errorMessage : PublishRelay<String> = PublishRelay.create()

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

  fun startAuthorize(callBack: String) {
    val tw = loginUseCase.createTwitterClientInstance(getApplication())
    loginUseCase.startAuthorize(tw, callBack)
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnError { errorMessage.accept(it.message) }
      .bindTo { requestToken.accept(it) }
      .addTo(disposable)
  }

  fun afterAuthorize(verifier : String) {
    val tw = loginUseCase.createTwitterClientInstance(getApplication())
    loginUseCase.afterAuthorize(tw, requestToken.blockingFirst(), verifier)
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnError { errorMessage.accept(it.message) }
      .bindTo { account.accept( Account(it.token, it.tokenSecret, it) ) }
      .addTo(disposable)
  }


}