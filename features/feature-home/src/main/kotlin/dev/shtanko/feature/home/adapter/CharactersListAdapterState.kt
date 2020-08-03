package dev.shtanko.feature.home.adapter

sealed class CharactersListAdapterState(
    val hasExtraRow: Boolean
) {

    object Added : CharactersListAdapterState(hasExtraRow = true)

    object AddLoading : CharactersListAdapterState(hasExtraRow = true)

    object AddError : CharactersListAdapterState(hasExtraRow = true)

    object NoMore : CharactersListAdapterState(hasExtraRow = true)

    fun isAdded() = this is Added

    fun isAddLoading() = this is AddLoading

    fun isAddError() = this is AddError

    fun isNoMore() = this is NoMore
}
