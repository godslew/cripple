package com.godslew.core.android.redux

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.domain.state.SessionState

object SessionReducer : Reducer<SessionState, AppAction.SessionAction>{
  override fun invoke(state: SessionState, action: AppAction.SessionAction): SessionState {
    return when(action) {
      is AppAction.SessionAction.TweetAction -> {
        when(action) {
          is AppAction.SessionAction.TweetAction.SendTweet -> TODO()
        }
      }
    }
  }
}