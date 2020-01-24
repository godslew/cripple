package com.godslew.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.godslew.account.databinding.FragmentAccountBinding
import com.godslew.core.android.app.AppRouterType
import com.godslew.core.android.extensions.bindTo
import com.godslew.core.android.factory.ViewModelFactory
import com.godslew.core.android.presenter.BaseFragment
import com.godslew.core.component.accounts.ItemAccount
import com.godslew.core.component.accounts.ItemAddAccount
import com.godslew.core.java.entity.Account
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


class AccountFragment : BaseFragment() {

  companion object {
    fun newInstance() = AccountFragment()
  }

  private val disposable = CompositeDisposable()

  @Inject
  internal lateinit var appRouter: AppRouterType

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
    setup()
  }

  override fun onResume() {
    super.onResume()
  }

  private fun setup() {
    val addAccount : () -> Unit = {
      startActivity(appRouter.getOAuthActivity(requireContext()))
    }

    val openAccount : (account : Account) -> Unit = {
      startActivity(appRouter.getTimelineCreatorActivity(requireContext(), it))
    }
    viewModel.accounts
      .observeOn(AndroidSchedulers.mainThread())
      .bindTo {
        val accounts = it.map { ac -> ItemAccount(ac, openAccount) }
        val addItem = ItemAddAccount(addAccount)
        val currentAdapter = binding.list.adapter
        if (currentAdapter is GroupAdapter) {
          currentAdapter.updateAsync(accounts + addItem)
        }else {
          val manager = LinearLayoutManager(context)
          val adapter = GroupAdapter<GroupieViewHolder>()
          binding.list.layoutManager = manager
          binding.list.adapter = adapter
          adapter.updateAsync(accounts + addItem)
        }
      }.addTo(disposable)

    viewModel.setup()
  }

}
