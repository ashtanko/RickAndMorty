package dev.shtanko.feature.filter

sealed class FilterViewEvent {
    object ApplyFilter : FilterViewEvent()
    object OpenTest : FilterViewEvent()
}
