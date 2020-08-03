package dev.shtanko.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_ID
import dev.shtanko.common.ui.base.BaseStatePagedListAdapter
import dev.shtanko.feature.home.HomeViewModel
import dev.shtanko.feature.home.model.CharacterModel
import javax.inject.Inject

internal enum class ItemView(val type: Int) {
    CHARACTER(type = 0),
    LOADING(type = 1),
    ERROR(type = 2);

    companion object {
        fun valueOf(type: Int): ItemView? = values().first { it.type == type }
    }
}

class CharacterAdapter @Inject constructor(
    private val viewModel: HomeViewModel
) : BaseStatePagedListAdapter<CharacterModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    private var state: CharactersListAdapterState = CharactersListAdapterState.Added

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemView(position)) {
            ItemView.CHARACTER ->
                getItem(position)?.let {
                    if (holder is CharacterViewHolder) {
                        holder.bind(viewModel, it)
                    }
                }
            ItemView.ERROR ->
                if (holder is ErrorViewHolder) {
                    holder.bind(viewModel)
                }
            else -> {
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (ItemView.valueOf(viewType)) {
            ItemView.CHARACTER -> CharacterViewHolder.create(inflater, parent)
            ItemView.LOADING -> LoadingViewHolder.create(inflater, parent)
            else -> ErrorViewHolder.create(inflater, parent)
        }

    override fun getItemId(position: Int) =
        when (getItemView(position)) {
            ItemView.CHARACTER -> getItem(position)?.id ?: NO_ID
            ItemView.LOADING -> 0L
            ItemView.ERROR -> 1L
        }

    override fun getItemCount() =
        if (state.hasExtraRow) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }

    override fun getItemViewType(position: Int) = getItemView(position).type

    fun submitState(newState: CharactersListAdapterState) {
        currentList?.let {
            if (it.isNotEmpty()) {
                val previousState = this.state
                val hadExtraRow = state.hasExtraRow
                state = newState
                val hasExtraRow = state.hasExtraRow
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState != state) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    private fun getItemView(position: Int) =
        if (state.hasExtraRow && position == itemCount - 1) {
            if (state.isAddError()) {
                ItemView.ERROR
            } else {
                ItemView.LOADING
            }
        } else {
            ItemView.CHARACTER
        }
}
