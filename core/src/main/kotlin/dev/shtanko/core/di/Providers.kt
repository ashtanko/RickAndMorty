package dev.shtanko.core.di

import dev.shtanko.core.App
import dev.shtanko.core.navigation.AppNavigation
import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.interactor.GetCharactersUseCase
import dev.shtanko.domain.interactor.GetDetailsUseCase
import dev.shtanko.domain.interactor.ObserveFilterUseCase
import dev.shtanko.domain.interactor.SaveFilterUseCase

interface ApplicationProvider : ToolsProvider, UseCaseProvider

interface ToolsProvider {
    fun provideContext(): App
    fun provideNavigation(): AppNavigation
}

interface UseCaseProvider {
    fun provideGetCharactersUseCase(): GetCharactersUseCase

    fun provideGetDetailsUseCase(): GetDetailsUseCase

    fun provideSaveFilterUseCase(): SaveFilterUseCase

    fun provideObserveFilterUseCase(): ObserveFilterUseCase

    fun provideExecuteSchedulers(): ExecuteSchedulers
}
