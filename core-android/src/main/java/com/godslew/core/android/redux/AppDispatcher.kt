package com.godslew.core.android.redux

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import timber.log.Timber

object AppDispatcher {

  private val relay = PublishRelay.create<ActionType>().toSerialized()

  fun dispatch(vararg events: ActionType) {
    events.forEach { event ->
      Timber.d("AppDispatcher: $event")
      relay.accept(event)
    }
  }

  fun <EVENT : ActionType> on(clazz: Class<EVENT>): Observable<EVENT> {
    return relay.ofType(clazz)
  }

}
