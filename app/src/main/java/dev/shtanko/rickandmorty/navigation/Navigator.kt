package dev.shtanko.rickandmorty.navigation

import androidx.navigation.NavController
import dev.shtanko.core.navigation.AppNavigation
import dev.shtanko.feature.home.HomeFragmentDirections
import javax.inject.Inject

class Navigator @Inject constructor() : AppNavigation {

    private var navController: NavController? = null

    override fun fromHomeToDetails(id: Long, title: String) {
        navController?.navigate(
            HomeFragmentDirections.actionHomeToDetails(id, title)
        )
    }

    override fun fromHomeToFilter() {
        navController?.navigate(
            HomeFragmentDirections.actionHomeToFilter()
        )
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
}
