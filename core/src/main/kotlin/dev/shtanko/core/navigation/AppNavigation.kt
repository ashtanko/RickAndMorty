package dev.shtanko.core.navigation

import androidx.navigation.NavController

interface AppNavigation : FilterNavigator, DetailsNavigator, HomeNavigator {

    fun bind(navController: NavController)

    fun unbind()
}
