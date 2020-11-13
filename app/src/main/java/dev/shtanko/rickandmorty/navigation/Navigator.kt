package dev.shtanko.rickandmorty.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import dev.shtanko.core.navigation.AppNavigation
import dev.shtanko.feature.filter.FilterFragmentDirections
import dev.shtanko.feature.home.HomeFragmentDirections
import javax.inject.Inject

class Navigator @Inject constructor() : AppNavigation {

    private var navController: NavController? = null

    override fun fromHomeToDetails(id: Long, title: String) {
        navigate(HomeFragmentDirections.actionHomeToDetails(id, title))
    }

    override fun fromHomeToFilter() {
        navigate(HomeFragmentDirections.actionHomeToFilter())
    }

    override fun navigateBack() {
        navController?.navigateUp()
    }

    override fun bind(navController: NavController) {
        this.navController = navController
    }

    override fun unbind() {
        navController = null
    }

    override fun fromFilterToTest() {
        navigate(FilterFragmentDirections.actionFilterToTest())
    }

    private fun navigate(directions: NavDirections) {
        navController?.navigate(
            directions
        )
    }
}
