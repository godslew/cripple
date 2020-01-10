package com.godslew.cripple.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ActivityMainBinding
import com.godslew.core.domain.usecase.LoginUseCase
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.tweet.TweetActivity
import com.godslew.gksettingpreferences.SettingPreferences
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject
  lateinit var loginUseCase: LoginUseCase
  @Inject
  lateinit var settingPreferences: SettingPreferences
  @Inject
  internal lateinit var factory: ViewModelFactory<MainActivityViewModel>

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
  private val viewModel: MainActivityViewModel by viewModels { factory }

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
