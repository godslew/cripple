package com.godslew.core.domain.usecase

import com.godslew.core.android.action.AppAction
import com.godslew.core.android.entity.Accounts
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.android.scope.AppScope
import com.godslew.gksettingpreferences.SettingPreferences
import io.reactivex.Single
import javax.inject.Inject

@AppScope
class LoginUseCase @Inject constructor(
  private val settingPreferences: SettingPreferences
) {
  fun hasLoginSession(): Single<Boolean> {
    return Single.create {
      val accounts = settingPreferences.load(AccountsStoreName, Accounts::class.java)
      if (accounts == null) {
        it.onSuccess(false)
        return@create
      }
      it.onSuccess(accounts.list.isNotEmpty())
      AppDispatcher.dispatch(AppAction.AccountAction.Load(accounts.list))
    }
  }

  companion object {
    private const val AccountsStoreName = "cripple_accounts"
  }
}