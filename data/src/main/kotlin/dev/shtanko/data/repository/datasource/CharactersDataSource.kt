package dev.shtanko.data.repository.datasource

import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.domain.model.CharacterResultEntity
import io.reactivex.Single

interface CharactersDataSource {
    fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        page: Int
    ): Single<CharacterResultEntity>

    fun getCharacter(id: Long): Single<CharacterEntity>
}
