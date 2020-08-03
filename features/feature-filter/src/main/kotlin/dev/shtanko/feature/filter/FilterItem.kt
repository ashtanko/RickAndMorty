package dev.shtanko.feature.filter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class FilterItem(@Bindable var isEnabled: Boolean = false, @Bindable var text: String = "") :
    BaseObservable() {

    fun fromFilter(data: Pair<Boolean, String>) {
        isEnabled = data.first
        text = data.second
        notifyChange()
    }
}
