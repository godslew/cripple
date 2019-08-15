package com.godslew.cripple.ui.tweet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godslew.cripple.R


class TweetFragment : Fragment() {

  companion object {
    fun newInstance() = TweetFragment()
  }

  private lateinit var viewModel: TweetViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.tweet_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(TweetViewModel::class.java)
    // TODO: Use the ViewModel
  }

}
