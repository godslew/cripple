package com.godslew.cripple.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ActivityMainBinding
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.core.java.Constants
import com.godslew.tweet.TweetActivity
import com.godslew.gksettingpreferences.SettingPreferences
import com.godslew.oauth.TwitterLoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject
  lateinit var settingPreferences: SettingPreferences
  @Inject
  internal lateinit var factory: ViewModelFactory<MainActivityViewModel>

  private val disposable  = CompositeDisposable()

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

    setup()
  }

  override fun onDestroy() {
    disposable.dispose()
    super.onDestroy()
  }

  private fun setup() {
    viewModel.isLogin
      .bindTo {
        startActivityForResult(TwitterLoginActivity.createIntent(this), Constants.RequestCodes.TwitterLogin)
    }
      .addTo(disposable)

    viewModel.setup()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (resultCode != Activity.RESULT_OK || data == null) {
      return
    }
  }
}
