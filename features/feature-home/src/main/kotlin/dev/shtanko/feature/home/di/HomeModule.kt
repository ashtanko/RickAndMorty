package dev.shtanko.feature.home.di

import dagger.Module
import dagger.Provides
import dev.shtanko.common.ui.extensions.viewModel
import dev.shtanko.core.di.scope.FragmentScope
import dev.shtanko.domain.interactor.GetCharactersUseCase
import dev.shtanko.domain.interactor.ObserveFilterUseCase
import dev.shtanko.feature.home.HomeFragment
import dev.shtanko.feature.home.HomeViewModel
import dev.shtanko.feature.home.adapter.CharacterAdapter
import dev.shtanko.feature.home.model.CharacterModelMapper

@Module
class HomeModule(
    private val fragment: HomeFragment
) {

    @Provides
    @FragmentScope
    fun provideCharacterAdapter(viewModel: HomeViewModel): CharacterAdapter {
        return CharacterAdapter(viewModel)
    }

    @Provides
    @FragmentScope
    fun provideHomeViewModel(
        observeFilterUseCase: ObserveFilterUseCase,
        getCharactersUseCase: GetCharactersUseCase,
        mapper: CharacterModelMapper
    ) = fragment.viewModel {
        HomeViewModel(observeFilterUseCase, getCharactersUseCase, mapper)
    }

    @FragmentScope
    @Provides
    fun providesCharacterModelMapper() = CharacterModelMapper()
}
