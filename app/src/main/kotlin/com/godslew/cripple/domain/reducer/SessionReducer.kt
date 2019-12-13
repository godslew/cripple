package com.godslew.cripple.domain.reducer

import com.freeletics.rxredux.Reducer
import com.godslew.cripple.domain.action.AppAction
import com.godslew.cripple.domain.state.SessionState

object SessionReducer : Reducer<SessionState, AppAction.SessionAction>{
  override fun invoke(state: SessionState, action: AppAction.SessionAction): SessionState {
    return when(action) {
      is AppAction.SessionAction.PostTweetAction -> {state}
    }
  }
}