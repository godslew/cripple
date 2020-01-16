package com.godslew.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.godslew.account.databinding.FragmentAccountBinding
import com.godslew.core.android.action.AppAction
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.core.android.redux.AppDispatcher
import javax.inject.Inject


class AccountFragment : BaseFragment() {

  companion object {
    fun newInstance() = AccountFragment()
  }

  @Inject
  internal lateinit var factory: ViewModelFactory<AccountViewModel>
  private val viewModel: AccountViewModel by viewModels { factory }
  private lateinit var binding: FragmentAccountBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentAccountBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    AppDispatcher.dispatch(AppAction.AppViewAction.MainViewAction.DisplayTweetButtonAction(false))
  }

  override fun onResume() {
    AppDispatcher.dispatch(AppAction.AppViewAction.MainViewAction.DisplayTweetButtonAction(false))
    super.onResume()
  }

}
