package dev.shtanko.data.di.modules

import dagger.Binds
import dagger.Module
import dev.shtanko.data.AppSchedulers
import dev.shtanko.data.repository.CharactersDataRepository
import dev.shtanko.data.repository.FilterDataRepository
import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.repository.CharactersRepository
import dev.shtanko.domain.repository.FilterRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindsSchedulers(impl: AppSchedulers): ExecuteSchedulers

    @Binds
    fun bindCharactersRepository(impl: CharactersDataRepository): CharactersRepository

    @Binds
    fun bindFilterRepository(impl: FilterDataRepository): FilterRepository
}
