package com.godslew.tweet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.toSpannable
import androidx.fragment.app.viewModels
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.tweet.databinding.FragmentTweetBinding
import twitter4j.Twitter
import javax.inject.Inject


class TweetFragment : BaseFragment() {

  companion object {
    private const val MaxInputTextLength = 140
    fun newInstance() = TweetFragment()
  }

  interface CustomTextWatcher: TextWatcher{
    override fun beforeTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {}
  }

  private lateinit var binding: FragmentTweetBinding
  private lateinit var text: String

  @Inject
  internal lateinit var twitter: Twitter
  @Inject
  internal lateinit var factory: ViewModelFactory<TweetViewModel>
  private val viewModel: TweetViewModel by viewModels { factory }


  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentTweetBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.buttonTweet.isEnabled = false
    binding.buttonTweet.setOnClickListener {
      viewModel.postTweet(requireContext(), text)
      requireActivity().finish()
    }
    binding.closeTweet.setOnClickListener {
      requireActivity().finish()
    }

    binding.textTweet.addTextChangedListener((object:
      CustomTextWatcher {
      override fun afterTextChanged(e: Editable?) {
        //ここに処理を書く
        val edit = e ?: return
        if (edit.isEmpty()) {
          binding.buttonTweet.isEnabled = false
          return
        }
        if (edit.length > MaxInputTextLength) {
          binding.buttonTweet.isEnabled = false
          return
        }
        binding.buttonTweet.isEnabled = true
        text = edit.toSpannable().toString()
      }
    }))
  }
}
