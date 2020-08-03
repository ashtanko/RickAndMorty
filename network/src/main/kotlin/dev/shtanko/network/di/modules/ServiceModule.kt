package dev.shtanko.network.di.modules

import dagger.Module
import dagger.Provides
import dev.shtanko.network.services.CharactersService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideCharactersService(retrofit: Retrofit): CharactersService {
        return retrofit.create(CharactersService::class.java)
    }
}
