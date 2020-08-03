package dev.shtanko.feature.detail.di.modules

import dagger.Module
import dagger.Provides
import dev.shtanko.common.ui.extensions.viewModel
import dev.shtanko.common.ui.views.ProgressBarDialog
import dev.shtanko.core.di.scope.FragmentScope
import dev.shtanko.domain.interactor.GetDetailsUseCase
import dev.shtanko.feature.detail.CharacterDetailFragment
import dev.shtanko.feature.detail.CharacterDetailViewModel

@Module
class CharacterDetailViewModule(private val fragment: CharacterDetailFragment) {

    @Provides
    fun provideCharacterDetailViewModel(
        getDetailsUseCase: GetDetailsUseCase
    ) = fragment.viewModel {
        CharacterDetailViewModel(getDetailsUseCase)
    }

    @FragmentScope
    @Provides
    fun providesProgressBarDialog() = ProgressBarDialog(context = fragment.requireContext())
}
