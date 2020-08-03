package dev.shtanko.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.shtanko.common.ui.base.BaseViewHolder
import dev.shtanko.feature.home.HomeViewModel
import dev.shtanko.feature.home.databinding.ItemErrorBinding

class ErrorViewHolder(
    binding: ItemErrorBinding
) : BaseViewHolder<ItemErrorBinding>(
    binding = binding
) {

    fun bind(viewModel: HomeViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup): ErrorViewHolder {
            val binding = ItemErrorBinding.inflate(
                inflater,
                parent,
                false
            )
            return ErrorViewHolder(binding)
        }
    }
}
