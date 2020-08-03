package dev.shtanko.common.ui.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.shtanko.common.ui.recyclerview.OffsetDecoration

@BindingAdapter("itemDecoration")
fun bindItemDecoration(view: RecyclerView, offset: Float) {
    view.addItemDecoration(
        OffsetDecoration(
            offset.toInt()
        )
    )
}
