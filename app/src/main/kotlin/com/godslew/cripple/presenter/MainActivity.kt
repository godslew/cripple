package com.godslew.cripple.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.godslew.cripple.R
import com.godslew.cripple.presenter.tweet.TweetActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

  private lateinit var appBarConfiguration: AppBarConfiguration

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val fab: FloatingActionButton = findViewById(R.id.fab)
    fab.setOnClickListener {
      startActivity(TweetActivity.createIntent(this))
    }
    val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
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
      ), drawerLayout
    )
  }
}
