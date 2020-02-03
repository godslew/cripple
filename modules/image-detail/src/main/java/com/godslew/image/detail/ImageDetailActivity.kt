package com.godslew.image.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.core.java.entity.CrippleStatus
import com.godslew.image.detail.databinding.ActivityImageDetailBinding

class ImageDetailActivity : BaseActivity() {

  private val  binding by lazy { ActivityImageDetailBinding.inflate(layoutInflater) }
  private val status by lazy { intent.getSerializableExtra(STATUS) as CrippleStatus }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(binding.fragmentContainer.id, ImageDetailFragment(status))
        .commitNow()
    }
    window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
  }

  private fun setupFragment() {
    supportFragmentManager
      .beginTransaction()
      .replace(binding.fragmentContainer.id, ImageDetailFragment(status))
      .commit()
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