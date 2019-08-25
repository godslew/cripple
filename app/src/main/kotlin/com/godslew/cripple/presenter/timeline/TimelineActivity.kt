package com.godslew.cripple.presenter.timeline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.AppBarConfiguration
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ActivityMainBinding
import com.godslew.cripple.presenter.tweet.TweetActivity

class TimelineActivity : AppCompatActivity(), AddTimelineTabListDialogFragment.Listener {
  override fun onAddTimelineTabClicked(position: Int) {
  }

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding

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
      setOf(
        R.id.nav_home,
        R.id.nav_gallery,
        R.id.nav_slideshow,
        R.id.nav_tools,
        R.id.nav_share,
        R.id.nav_send
      ), binding.drawerLayout
    )
    //AddTimelineTabListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
  }
}
