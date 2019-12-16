package com.godslew.core.java.entity

import twitter4j.auth.AccessToken

data class Account(
  val consumerKey : String,
  val consumerSecret: String,
  val accessToken: AccessToken
) {
  companion object {
    fun initialize() : Account {
      return Account("", "", AccessToken("", ""))
    }
  }
}