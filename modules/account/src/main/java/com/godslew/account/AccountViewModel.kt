package com.godslew.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.store.AppStore
import com.godslew.core.java.entity.Account
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountViewModel @Inject constructor(
  application: Application,
  private val appStore: AppStore
) : AndroidViewModel(application) {

  private val disposable = CompositeDisposable()

  val accounts : PublishRelay<List<Account>> = PublishRelay.create()

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

  fun setup() {
    appStore
      .observable()
      .map { it.accountList }
      .distinctUntilChanged()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.newThread())
      .bindTo { accounts.accept(it) }
      .addTo(disposable)
  }
}
