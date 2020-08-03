package dev.shtanko.feature.detail

import dev.shtanko.common.ui.base.BaseViewState

sealed class CharacterDetailViewState : BaseViewState {

    object Loading : CharacterDetailViewState()

    object Loaded : CharacterDetailViewState()

    object Error : CharacterDetailViewState()

    object Dismiss : CharacterDetailViewState()

    fun isLoading() = this is Loading

    fun isError() = this is Error

    fun isDismiss() = this is Dismiss
}
