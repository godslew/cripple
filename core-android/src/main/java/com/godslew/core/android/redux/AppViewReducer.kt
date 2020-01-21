package com.godslew.core.android.redux

import com.freeletics.rxredux.Reducer
import com.godslew.core.android.action.AppAction
import com.godslew.core.domain.state.viewstate.AppViewState

object AppViewReducer : Reducer<AppViewState, AppAction.AppViewAction>{
  override fun invoke(state: AppViewState, action: AppAction.AppViewAction): AppViewState {
    return when(action) {
      is AppAction.AppViewAction.MainViewAction -> state.copy(mainViewState = MainViewReducer.invoke(state.mainViewState, action))
    }
  }
}