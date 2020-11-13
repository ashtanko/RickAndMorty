package dev.shtanko.feature.filter

import android.os.Bundle
import android.view.View
import dev.shtanko.common.ui.base.BaseViewModelFragment
import dev.shtanko.feature.filter.databinding.FragmentTestBinding

class TestFragment :
    BaseViewModelFragment<FragmentTestBinding, FilterViewModel>(
        layoutId = R.layout.fragment_test
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initDataBinding() {
    }

    override fun inject() {
    }
}
