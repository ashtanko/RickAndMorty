package dev.shtanko.data.di.modules

import dagger.Module
import dagger.Provides
import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.interactor.GetCharactersUseCase
import dev.shtanko.domain.interactor.GetDetailsUseCase
import dev.shtanko.domain.interactor.ObserveFilterUseCase
import dev.shtanko.domain.interactor.SaveFilterUseCase
import dev.shtanko.domain.repository.CharactersRepository
import dev.shtanko.domain.repository.FilterRepository
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(
        repository: CharactersRepository,
        executeSchedulers: ExecuteSchedulers
    ): GetCharactersUseCase {
        return GetCharactersUseCase(repository, executeSchedulers)
    }

    @Provides
    @Singleton
    fun provideGetDetailsUseCase(
        repository: CharactersRepository,
        executeSchedulers: ExecuteSchedulers
    ): GetDetailsUseCase {
        return GetDetailsUseCase(repository, executeSchedulers)
    }

    @Provides
    @Singleton
    fun provideSaveFilterUseCase(
        repository: FilterRepository,
        executeSchedulers: ExecuteSchedulers
    ): SaveFilterUseCase {
        return SaveFilterUseCase(repository, executeSchedulers)
    }

    @Provides
    @Singleton
    fun provideObserveFilterUseCase(
        repository: FilterRepository,
        executeSchedulers: ExecuteSchedulers
    ): ObserveFilterUseCase {
        return ObserveFilterUseCase(repository, executeSchedulers)
    }
}
