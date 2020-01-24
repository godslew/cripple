package com.godslew.timeline.creator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.core.java.entity.Account
import com.godslew.timeline.creator.databinding.ActivityTimelineCreatorBinding
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TimelineCreatorActivity : BaseActivity() {

  @Inject
  internal lateinit var factory: ViewModelFactory<TimelineCreatorViewModel>

  private val disposable  = CompositeDisposable()

  private val binding by lazy { ActivityTimelineCreatorBinding.inflate(layoutInflater) }
  private val account by lazy { intent.getSerializableExtra(ACCOUNT) as Account }
  private val viewModel: TimelineCreatorViewModel by viewModels { factory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
    setupToolbar()
    setupFragment()
  }

  override fun onDestroy() {
    disposable.dispose()
    super.onDestroy()
  }

  private fun setupToolbar() {
    binding.toolbar.setNavigationIcon(R.drawable.ic_close_white_18dp)
    binding.toolbar.title = account.screenname()
    setSupportActionBar(binding.toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeButtonEnabled(true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> finish()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun setupFragment() {
    supportFragmentManager
      .beginTransaction()
      .replace(binding.fragmentContainer.id, TimelineCreatorFragment(account))
      .commit()
  }

  companion object {
    private const val ACCOUNT = "account"
    fun createIntent(context: Context, account: Account): Intent {
      return Intent(context, TimelineCreatorActivity::class.java).apply {
        val bundle = Bundle().apply {
          putSerializable(ACCOUNT, account)
        }
        putExtras(bundle)
      }
    }
  }
}