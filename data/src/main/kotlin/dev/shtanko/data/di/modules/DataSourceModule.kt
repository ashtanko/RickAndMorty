package dev.shtanko.data.di.modules

import dagger.Binds
import dagger.Module
import dev.shtanko.data.repository.datasource.CharactersDataSource
import dev.shtanko.data.repository.datasource.FilterDataSource
import dev.shtanko.data.repository.datasource.local.FilterDataSourceImpl
import dev.shtanko.data.repository.datasource.remote.CharactersRemoteDataSource
import javax.inject.Singleton

@Module
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindFilterDataSource(impl: FilterDataSourceImpl): FilterDataSource

    @Binds
    @Singleton
    fun bindCharactersDataSource(impl: CharactersRemoteDataSource): CharactersDataSource
}
