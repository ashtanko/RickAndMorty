package dev.shtanko.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.shtanko.common.ui.base.BaseViewHolder
import dev.shtanko.feature.home.HomeViewModel
import dev.shtanko.feature.home.databinding.ItemCharacterBinding
import dev.shtanko.feature.home.model.CharacterModel

class CharacterViewHolder(binding: ItemCharacterBinding) :
    BaseViewHolder<ItemCharacterBinding>(
        binding = binding
    ) {

    fun bind(viewModel: HomeViewModel, model: CharacterModel) {
        binding.viewModel = viewModel
        binding.character = model
        binding.executePendingBindings()
    }

    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup): CharacterViewHolder {
            val binding = ItemCharacterBinding.inflate(
                inflater,
                parent,
                false
            )
            return CharacterViewHolder(binding)
        }
    }
}
