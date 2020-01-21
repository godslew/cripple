package com.godslew.core.domain.reducer

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.domain.state.viewstate.MainViewState

object MainViewReducer : Reducer<MainViewState, AppAction.AppViewAction.MainViewAction>{
  override fun invoke(state: MainViewState, action: AppAction.AppViewAction.MainViewAction): MainViewState {
    return when(action) {
      is AppAction.AppViewAction.MainViewAction.DisplayTweetButtonAction -> state.copy(isShowTweetButton = action.isShow)
    }
  }
}