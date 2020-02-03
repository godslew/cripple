package com.godslew.core.java.entity

import twitter4j.Status
import java.io.Serializable

data class CrippleStatus(
  val status: Status,
  val twitterID: Long
) : Serializable