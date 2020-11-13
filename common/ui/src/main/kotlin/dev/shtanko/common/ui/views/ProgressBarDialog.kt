package dev.shtanko.common.ui.views

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import dev.shtanko.common.ui.R
import dev.shtanko.common.ui.databinding.ViewProgressDialogBinding

class ProgressBarDialog(
    context: Context
) : AlertDialog(context, R.style.CustomProgressDialog) {

    private lateinit var viewBinding: ViewProgressDialogBinding

    override fun show() {
        show(R.string.loading_title)
    }

    fun show(@StringRes messageRes: Int?) {
        super.show()
        viewBinding = ViewProgressDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(viewBinding.root)
        setCanceledOnTouchOutside(false)
        setCancelable(false)

        viewBinding.isLoading = true
        viewBinding.message = messageRes?.let { context.getString(it) }
    }

    fun dismissWithErrorMessage(@StringRes messageRes: Int) {
        setCanceledOnTouchOutside(true)
        setCancelable(true)

        viewBinding.isLoading = false
        viewBinding.message = context.getString(messageRes)
    }
}
