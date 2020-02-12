package com.godslew.image.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.image.detail.databinding.ActivityImageDetailBinding
import javax.inject.Inject

class ImageDetailActivity : BaseActivity() {

  private val binding by lazy { ActivityImageDetailBinding.inflate(layoutInflater) }
  private val status by lazy { intent.getSerializableExtra(STATUS) as CrippleStatus }
  @Inject
  internal lateinit var factory: ViewModelFactory<ImageDetailViewModel>
  private val viewModel: ImageDetailViewModel by viewModels { factory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    setupFragment()
    setupToolbar()
  }

  private fun setupFragment() {
    supportFragmentManager
      .beginTransaction()
      .replace(binding.fragmentContainer.id, ImageDetailFragment(status))
      .commit()
  }

  private fun setupToolbar() {
    window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
    binding.toolbar.setNavigationIcon(R.drawable.ic_close_white_18dp)
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

  companion object {
    private const val STATUS = "status"
    fun createIntent(context: Context, status : CrippleStatus) : Intent {
      return Intent(context, ImageDetailActivity::class.java).apply {
        val bundle = Bundle().apply {
          putSerializable(STATUS, status)
        }
        putExtras(bundle)
      }
    }
  }
}