package com.godslew.cripple.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ActivityMainBinding
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.java.Constants
import com.godslew.tweet.TweetActivity
import com.godslew.gksettingpreferences.SettingPreferences
import com.godslew.oauth.TwitterLoginActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
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
    navController.addOnDestinationChangedListener { _, destination, _ ->
      binding.fab.isVisible = destination.label == "TimelineFragment"
    }

    setup()
  }

  override fun onDestroy() {
    disposable.dispose()
    super.onDestroy()
  }

  private fun setup() {
    viewModel.loadedAccounts
      .bindTo {
        if (it.isNotEmpty()) {
          AppDispatcher.dispatch(AppAction.AccountAction.ChangeCurrentAction(it.first()))
          AppDispatcher.dispatch(AppAction.AccountAction.LoadAccountAction(it))
        } else {
          startActivityForResult(
            TwitterLoginActivity.createIntent(this),
            Constants.RequestCodes.TwitterLogin
          )
        }
    }.addTo(disposable)

    viewModel.setup()
  }
}
