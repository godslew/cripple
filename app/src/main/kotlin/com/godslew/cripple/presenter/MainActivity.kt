package com.godslew.cripple.presenter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.godslew.account.AccountActivity
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.core.android.redux.AppDispatcher
import com.godslew.core.java.Constants
import com.godslew.cripple.R
import com.godslew.cripple.databinding.ActivityMainBinding
import com.godslew.gksettingpreferences.SettingPreferences
import com.godslew.oauth.TwitterLoginActivity
import com.godslew.timeline.TimelineFragment
import com.godslew.tweet.TweetActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


class MainActivity : BaseActivity() {

  @Inject
  internal lateinit var settingPreferences: SettingPreferences

  @Inject
  internal lateinit var factory: ViewModelFactory<MainActivityViewModel>

  private val disposable = CompositeDisposable()

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
  private val viewModel: MainActivityViewModel by viewModels { factory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)

    binding.fab.setOnClickListener {
      startActivity(TweetActivity.createIntent(this))
    }
    setup()
  }

  override fun onDestroy() {
    disposable.dispose()
    super.onDestroy()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.item_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.menu_account -> {
        startActivity(AccountActivity.createIntent(this))
      }
      R.id.menu_setting -> {

      }
    }
    return true
  }

  private fun setup() {
    setupTimeline()
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

  private fun setupTimeline() {
    supportFragmentManager.beginTransaction().add(R.id.fragment, TimelineFragment()).commit()
  }
}
