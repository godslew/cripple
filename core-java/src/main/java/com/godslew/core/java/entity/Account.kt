package com.godslew.core.java.entity

import twitter4j.auth.AccessToken
import java.io.Serializable

data class Account(
  val consumerKey : String,
  val consumerSecret: String,
  val accessToken: AccessToken
) : Serializable {
  companion object {
    fun initialize() : Account {
      return Account("", "", AccessToken("", ""))
    }
  }

  fun userId() : Long {
    return accessToken.userId
  }

  fun screenname() : String {
    return accessToken.screenName
  }
}