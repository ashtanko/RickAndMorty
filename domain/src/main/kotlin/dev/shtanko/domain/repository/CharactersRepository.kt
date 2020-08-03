package dev.shtanko.domain.repository

import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.domain.model.CharacterResultEntity
import io.reactivex.Single

interface CharactersRepository {

    fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        page: Int
    ): Single<CharacterResultEntity>

    fun getCharacter(id: Long): Single<CharacterEntity>
}
