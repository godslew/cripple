package com.godslew.cripple.domain.entity

import twitter4j.auth.AccessToken

data class Account(
  val consumerKey : String,
  val consumerSecret: String,
  val accessToken: AccessToken
)