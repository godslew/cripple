package com.godslew.oauth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.domain.usecase.LoginUseCase
import com.godslew.core.java.entity.Account
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import twitter4j.auth.RequestToken
import javax.inject.Inject

class TwitterLoginViewModel @Inject constructor(
  application: Application,
  private val loginUseCase: LoginUseCase
) : AndroidViewModel(application) {

  private val disposable = CompositeDisposable()

  val requestToken: BehaviorRelay<RequestToken> = BehaviorRelay.create()
  val account: PublishRelay<Account> = PublishRelay.create()
  val finishRegistered : PublishRelay<Unit> = PublishRelay.create()
  val errorMessage: PublishRelay<String> = PublishRelay.create()

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

  fun startAuthorize(callBack: String) {
    loginUseCase.startAuthorize(getApplication(), callBack)
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnError { errorMessage.accept(it.message) }
      .bindTo { requestToken.accept(it) }
      .addTo(disposable)
  }

  fun afterAuthorize(verifier: String) {
    loginUseCase.afterAuthorize(getApplication(), requestToken.value, verifier)
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnError { errorMessage.accept(it.message) }
      .bindTo {
        account.accept(loginUseCase.createAccount(getApplication(), it))
      }
      .addTo(disposable)
  }

  fun registerAccount(account: Account) {
    loginUseCase.registerAccount(account)
      .subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .bindTo {
        loginUseCase.saveAccount(it)
        AppDispatcher.dispatch(AppAction.AccountAction.RegisterAction(it))
        if (it.size == 1) {
          AppDispatcher.dispatch(AppAction.AccountAction.ChangeCurrentAction(it.first()))
        }
        finishRegistered.accept(Unit)
      }
      .addTo(disposable)
  }

}