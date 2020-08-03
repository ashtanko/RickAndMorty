package dev.shtanko.feature.detail.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.shtanko.common.ui.viewmodel.ViewModelKey
import dev.shtanko.feature.detail.CharacterDetailViewModel

@Module
interface CharacterDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    fun bindCharacterDetailViewModel(viewModel: CharacterDetailViewModel): ViewModel
}
