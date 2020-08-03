package dev.shtanko.network.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.shtanko.network.api.CharactersApi
import dev.shtanko.network.api.CharactersNetwork
import dev.shtanko.network.services.CharactersService
import javax.inject.Singleton

@Module
class ApiModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideCharactersApi(service: CharactersService): CharactersApi {
        return CharactersNetwork(service, context)
    }
}
