package dev.shtanko.common.ui.bindings

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import dev.shtanko.common.ui.glide.GlideApp

@SuppressLint("CheckResult")
@BindingAdapter("imageUrl", "imagePlaceholder", requireAll = false)
fun ImageView.imageUrl(url: String?, @DrawableRes placeholderId: Int?) {
    GlideApp.with(this).load(url).centerCrop().into(this)
}
