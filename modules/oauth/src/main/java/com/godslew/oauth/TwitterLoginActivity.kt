package com.godslew.oauth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseActivity
import com.godslew.oauth.databinding.ActivityTwitterLoginBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TwitterLoginActivity : BaseActivity() {

  companion object {
    private const val QueryParam = "oauth_verifier"
    fun createIntent(context: Context) : Intent {
      return Intent(context, TwitterLoginActivity::class.java)
    }
  }

  private val disposable = CompositeDisposable()

  @Inject
  internal lateinit var factory: ViewModelFactory<TwitterLoginViewModel>

  private val binding by lazy { ActivityTwitterLoginBinding.inflate(layoutInflater) }
  private val viewModel: TwitterLoginViewModel by viewModels { factory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

    setup()
  }

  private fun setup() {
    binding.button.setOnClickListener {
      viewModel.startAuthorize(getString(R.string.twitter_callback_url))
    }
    viewModel.requestToken
      .bindTo {
        if (it.authorizationURL.isNotEmpty()) {
          val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.authorizationURL))
          startActivity(intent)
        } else {
          showToast(getString(R.string.twitter_oauth_failed))
        }
      }.addTo(disposable)

    viewModel.account
      .bindTo {
        showToast(getString(R.string.twitter_oauth_success))
        viewModel.registerAccount(it)
      }.addTo(disposable)

    viewModel.finishRegistered
      .bindTo {
        finish()
      }.addTo(disposable)

    viewModel.errorMessage
      .bindTo {
        showToast(it)
      }.addTo(disposable)

  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    if (intent == null
      || intent.data == null
      || !intent.data.toString().startsWith(getString(R.string.twitter_callback_url))
    ) {
      showToast(getString(R.string.twitter_oauth_failed))
      return
    }
      val verifier = intent.data?.getQueryParameter(QueryParam) ?: ""
      if (verifier.isEmpty()) {
        showToast(getString(R.string.twitter_oauth_failed))
      }
      viewModel.afterAuthorize(verifier)
  }
  private fun showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
  }
}