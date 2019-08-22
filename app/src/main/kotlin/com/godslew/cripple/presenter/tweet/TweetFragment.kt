package com.godslew.cripple.presenter.tweet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godslew.cripple.R
import com.godslew.cripple.databinding.FragmentTweetBinding


class TweetFragment : Fragment() {

  companion object {
    fun newInstance() = TweetFragment()
  }

  private lateinit var viewModel: TweetViewModel
  private lateinit var binding: FragmentTweetBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentTweetBinding.inflate(LayoutInflater.from(context), container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.buttonTweet.setOnClickListener {
      activity?.finish()
    }
    binding.closeTweet.setOnClickListener {
      activity?.finish()
    }
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(TweetViewModel::class.java)
    // TODO: Use the ViewModel
  }

}
