package com.godslew.cripple.presenter.tweet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.toSpannable
import com.godslew.cripple.R
import com.godslew.cripple.databinding.FragmentTweetBinding


class TweetFragment : Fragment() {

  companion object {
    private const val MaxInputTextLength = 140
    fun newInstance() = TweetFragment()
  }

  interface CustomTextWatcher: TextWatcher{
    override fun beforeTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {}
  }

  private lateinit var viewModel: TweetViewModel
  private lateinit var binding: FragmentTweetBinding
  private lateinit var text: String

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentTweetBinding.inflate(LayoutInflater.from(context), container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.buttonTweet.isEnabled = false
    binding.buttonTweet.setOnClickListener {
      viewModel.postTweet(requireContext(), text)
      activity?.finish()
    }
    binding.closeTweet.setOnClickListener {
      activity?.finish()
    }

    binding.textTweet.addTextChangedListener((object: CustomTextWatcher{
      override fun afterTextChanged(e: Editable?) {
        //ここに処理を書く
        val edit = e ?: return
        if (edit.isEmpty()) {
          binding.buttonTweet.isEnabled = false
          return
        }
        if (edit.length > 140) {
          binding.buttonTweet.isEnabled = false
          return
        }
        binding.buttonTweet.isEnabled = true
        text = edit.toSpannable().toString()
      }
    }))
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(TweetViewModel::class.java)
    // TODO: Use the ViewModel
  }

}
