package com.godslew.cripple.behavior

import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment

class CustomNavHostFragment : NavHostFragment() {
  override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> {
    return CustomNavigator(requireContext(), childFragmentManager, id)
  }
}