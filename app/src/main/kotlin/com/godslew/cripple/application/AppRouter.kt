package com.godslew.cripple.application

import android.content.Context
import android.content.Intent
import com.godslew.core.android.app.AppRouterType
import com.godslew.core.java.entity.Account
import com.godslew.oauth.TwitterLoginActivity
import com.godslew.timeline.creator.TimelineCreatorActivity
import com.godslew.tweet.TweetActivity

class AppRouter : AppRouterType{
  override fun getTweetActivity(context: Context): Intent {
    return TweetActivity.createIntent(context)
  }

  override fun getOAuthActivity(context: Context): Intent {
    return TwitterLoginActivity.createIntent(context)
  }

  override fun getTimelineCreatorActivity(context: Context, account: Account): Intent {
    return TimelineCreatorActivity.createIntent(context, account)
  }
}