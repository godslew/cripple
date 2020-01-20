package com.godslew.core.domain.usecase

import android.content.Context
import com.godslew.core.android.scope.AppScope
import com.godslew.core.android.utils.TwitterUtils
import com.godslew.core.domain.repository.AccountRepository
import com.godslew.core.domain.repository.TwitterRepository
import com.godslew.core.java.entity.Account
import io.reactivex.Single
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken
import javax.inject.Inject

@AppScope
class LoginUseCase @Inject constructor(
  private val twitterRepository: TwitterRepository,
  private val accountRepository: AccountRepository
) {

  fun loadAccounts(): Single<List<Account>> {
    return accountRepository.getAccounts()
  }

  fun createAccount(context: Context, accessToken: AccessToken) : Account {
    val keyPair = TwitterUtils.clientKey(context)
    val (consumerKey, consumerSecret) = keyPair
    return Account(consumerKey, consumerSecret, accessToken)
  }


  fun startAuthorize(context: Context, callBack : String) : Single<RequestToken> {
    val tw = TwitterUtils.createTwitterClientInstance(context)
    return twitterRepository.getRequestToken(tw, callBack)
  }

  fun afterAuthorize(context: Context, requestToken : RequestToken, verifier : String) : Single<AccessToken> {
    val tw = TwitterUtils.createTwitterClientInstance(context)
    return twitterRepository.getAccessToken(tw, requestToken, verifier)
  }

  fun registerAccount(account: Account) : Single<List<Account>> {
    return accountRepository.registerAccount(account)
  }

  fun saveAccount(accounts: List<Account>) {
    accountRepository.saveAccount(accounts)
  }
}