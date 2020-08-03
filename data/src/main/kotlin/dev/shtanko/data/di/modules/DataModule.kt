package dev.shtanko.data.di.modules

import dagger.Module
import dagger.Provides
import dev.shtanko.data.mapper.CharacterMapper
import dev.shtanko.data.mapper.CharactersMapper

@Module
class DataModule {

    @Provides
    fun provideCharactersMapper(): CharactersMapper {
        return CharactersMapper()
    }

    @Provides
    fun provideCharacterMapper(): CharacterMapper {
        return CharacterMapper()
    }
}
