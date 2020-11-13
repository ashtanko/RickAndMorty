package dev.shtanko.core.navigation

interface HomeNavigator : BackNavigator {

    fun fromHomeToDetails(id: Long, title: String)

    fun fromHomeToFilter()
}
