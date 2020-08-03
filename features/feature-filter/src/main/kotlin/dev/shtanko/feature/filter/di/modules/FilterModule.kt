package dev.shtanko.feature.filter.di.modules

import dagger.Module
import dagger.Provides
import dev.shtanko.common.ui.extensions.viewModel
import dev.shtanko.domain.interactor.ObserveFilterUseCase
import dev.shtanko.domain.interactor.SaveFilterUseCase
import dev.shtanko.feature.filter.FilterFragment
import dev.shtanko.feature.filter.FilterViewModel

@Module
class FilterModule(private val fragment: FilterFragment) {
    @Provides
    fun provideFilterViewModel(
        saveFilterUseCase: SaveFilterUseCase,
        observeFilterUseCase: ObserveFilterUseCase
    ) = fragment.viewModel {
        FilterViewModel(saveFilterUseCase, observeFilterUseCase)
    }
}
