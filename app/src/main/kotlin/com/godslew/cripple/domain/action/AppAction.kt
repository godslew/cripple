package com.godslew.cripple.domain.action

import twitter4j.Status

sealed class AppAction : ActionType {
  sealed class SessionAction : AppAction() {
    sealed class PostTweetAction : SessionAction() {
      data class SendTweet(val status: Status) : PostTweetAction()
    }
  }
}