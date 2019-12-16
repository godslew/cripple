package com.godslew.cripple.presenter.timeline

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ActivityMainBinding
import com.godslew.cripple.domain.usecase.LoginUseCase
import com.godslew.cripple.presenter.BaseActivity
import com.godslew.cripple.presenter.tweet.TweetActivity
import com.godslew.gksettingpreferences.SettingPreferences
import javax.inject.Inject

class MainActivity : BaseActivity() {
  private lateinit var binding: ActivityMainBinding
  @Inject
  lateinit var loginUseCase: LoginUseCase
  @Inject
  lateinit var settingPreferences: SettingPreferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    setSupportActionBar(binding.toolbar)

    binding.fab.setOnClickListener {
      startActivity(TweetActivity.createIntent(this))
    }
    val navController = findNavController(R.id.nav_host_fragment)
    setupWithNavController(binding.bottomNavigation, navController)
  }
}