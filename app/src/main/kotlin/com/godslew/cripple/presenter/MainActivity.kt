package com.godslew.cripple.presenter

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ActivityMainBinding
import com.godslew.core.domain.usecase.LoginUseCase
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.tweet.TweetActivity
import com.godslew.gksettingpreferences.SettingPreferences
import javax.inject.Inject

class MainActivity : BaseActivity() {
  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  @Inject
  lateinit var loginUseCase: LoginUseCase
  @Inject
  lateinit var settingPreferences: SettingPreferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)

    binding.fab.setOnClickListener {
      startActivity(TweetActivity.createIntent(this))
    }
    val navController = findNavController(R.id.nav_host_fragment)
    setupWithNavController(binding.bottomNavigation, navController)
  }
}
