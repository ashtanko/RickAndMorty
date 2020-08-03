package dev.shtanko.feature.home

import dev.shtanko.common.ui.base.BaseViewState

sealed class HomeViewState : BaseViewState {
    object Refreshing : HomeViewState()

    object Loaded : HomeViewState()

    object Loading : HomeViewState()

    object Empty : HomeViewState()

    object Error : HomeViewState()

    object NoMoreElements : HomeViewState()

    object AddLoading : HomeViewState()

    object AddError : HomeViewState()

    fun isRefreshing() = this is Refreshing

    fun isLoaded() = this is Loaded

    fun isLoading() = this is Loading

    fun isEmpty() = this is Empty

    fun isError() = this is Error

    fun isNoMoreElements() = this is NoMoreElements
}
