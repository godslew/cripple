package com.godslew.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.godslew.account.databinding.ActivityAccountBinding
import com.godslew.core.android.presenter.BaseActivity
import io.reactivex.disposables.CompositeDisposable

class AccountActivity : BaseActivity() {
  companion object {
    fun createIntent(context: Context): Intent {
      return Intent(context, AccountActivity::class.java)
    }
  }
  private val disposable  = CompositeDisposable()

  private val binding by lazy { ActivityAccountBinding.inflate(layoutInflater) }
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
    binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
    binding.toolbar.title = getString(R.string.menu_accounts)
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
      .replace(binding.fragmentContainer.id, AccountFragment.newInstance())
      .commit()
  }
}