package com.godslew.cripple.presenter.timeline

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.AppBarConfiguration
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ActivityMainBinding
import com.godslew.cripple.domain.usecase.LoginUseCase
import com.godslew.cripple.presenter.BaseActivity
import com.godslew.cripple.presenter.tweet.TweetActivity
import javax.inject.Inject

class TimelineActivity : BaseActivity(), AddTimelineTabListDialogFragment.Listener {
  override fun onAddTimelineTabClicked(position: Int) {
  }

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding
  @Inject
  lateinit var loginUseCase: LoginUseCase

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, TimelineFragment.newInstance())
        .commitNow()
    }
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    binding.appBar.fab.setOnClickListener {
      startActivity(TweetActivity.createIntent(this))
    }
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    appBarConfiguration = AppBarConfiguration(
      topLevelDestinationIds = setOf(
        R.id.nav_home,
        R.id.nav_gallery,
        R.id.nav_slideshow,
        R.id.nav_tools,
        R.id.nav_share,
        R.id.nav_send
      ), drawerLayout = binding.drawerLayout
    )
    println(loginUseCase.hasLoginSession())
    //AddTimelineTabListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
  }
}
