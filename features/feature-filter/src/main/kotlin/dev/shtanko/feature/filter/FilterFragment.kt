package dev.shtanko.feature.filter

import android.content.Context
import android.os.Bundle
import android.view.View
import dev.shtanko.common.ui.base.BaseViewModelFragment
import dev.shtanko.common.ui.extensions.observe
import dev.shtanko.core.App
import dev.shtanko.core.navigation.AppNavigation
import dev.shtanko.feature.filter.databinding.FragmentFilterBinding
import dev.shtanko.feature.filter.di.component.FilterComponent
import javax.inject.Inject

class FilterFragment :
    BaseViewModelFragment<FragmentFilterBinding, FilterViewModel>(
        layoutId = R.layout.fragment_filter
    ) {

    @Inject
    lateinit var navigation: AppNavigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
        viewModel.listenToFilter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.event, ::onViewEvent)
    }

    override fun initDataBinding() {
        viewBinding.viewModel = viewModel
    }

    override fun inject() {
        FilterComponent.Initializer
            .init((requireContext().applicationContext as App).getAppComponent(), this)
            .inject(this)
    }

    private fun onViewEvent(viewEvent: FilterViewEvent) {
        when (viewEvent) {
            is FilterViewEvent.ApplyFilter -> navigation.navigateBack()
            FilterViewEvent.OpenTest -> navigation.fromFilterToTest()
        }
    }
}
