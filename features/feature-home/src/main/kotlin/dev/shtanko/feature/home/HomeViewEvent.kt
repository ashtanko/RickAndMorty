package dev.shtanko.feature.home

sealed class HomeViewEvent {
    data class OpenCharacterDetail(val id: Long, val title: String) : HomeViewEvent()
}
