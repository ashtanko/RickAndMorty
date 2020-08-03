package dev.shtanko.common.ui.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.shtanko.common.ui.viewmodel.ViewModelFactory
import javax.inject.Singleton

@Module
interface ViewModelModule {
    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
