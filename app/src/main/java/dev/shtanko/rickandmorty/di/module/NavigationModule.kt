package dev.shtanko.rickandmorty.di.module

import dagger.Binds
import dagger.Module
import dev.shtanko.core.navigation.AppNavigation
import dev.shtanko.rickandmorty.navigation.Navigator
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigation(impl: Navigator): AppNavigation
}
