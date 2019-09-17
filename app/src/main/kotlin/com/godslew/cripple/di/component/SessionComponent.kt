package com.godslew.cripple.di.component

import com.godslew.cripple.di.module.SessionModule
import com.godslew.cripple.di.scope.SessionScope
import com.godslew.cripple.presenter.tweet.TweetFragment
import dagger.Subcomponent

@SessionScope
@Subcomponent(modules = [SessionModule::class])
interface SessionComponent {
  fun inject(fragment: TweetFragment)
}
