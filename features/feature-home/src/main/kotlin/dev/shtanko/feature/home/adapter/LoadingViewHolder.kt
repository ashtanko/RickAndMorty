package dev.shtanko.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.shtanko.common.ui.base.BaseViewHolder
import dev.shtanko.feature.home.databinding.ItemLoadingBinding

class LoadingViewHolder(
    binding: ItemLoadingBinding
) : BaseViewHolder<ItemLoadingBinding>(binding = binding) {

    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup): LoadingViewHolder {
            val binding = ItemLoadingBinding.inflate(
                inflater,
                parent,
                false
            )

            return LoadingViewHolder(binding)
        }
    }
}
