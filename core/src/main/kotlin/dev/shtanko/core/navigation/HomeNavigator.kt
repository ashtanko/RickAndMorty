package dev.shtanko.core.navigation

interface HomeNavigator {

    fun fromHomeToDetails(id: Long, title: String)

    fun fromHomeToFilter()

    fun navigateBack()
}
