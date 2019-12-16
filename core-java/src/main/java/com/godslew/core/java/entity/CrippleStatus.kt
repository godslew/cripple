package com.godslew.cripple.domain.entity

import twitter4j.Status

data class CrippleStatus(val status: Status,
                         val twitterID: Long)