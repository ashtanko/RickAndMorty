package dev.shtanko.common.ui.bindings

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import dev.shtanko.common.ui.glide.GlideApp

@SuppressLint("CheckResult")
@BindingAdapter("imageUrl", "imagePlaceholder", requireAll = false)
fun ImageView.imageUrl(url: String?, @DrawableRes placeholderId: Int?) {
    val glide = GlideApp.with(this).load(url)
    if (placeholderId == null) {
        glide.centerCrop().into(this)
    } else {
        glide.centerCrop().placeholder(placeholderId).into(this)
    }
}
