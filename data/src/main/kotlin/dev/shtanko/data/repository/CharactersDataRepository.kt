package dev.shtanko.data.repository

import dev.shtanko.data.repository.datasource.CharactersDataSource
import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.domain.model.CharacterResultEntity
import dev.shtanko.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class CharactersDataRepository @Inject constructor(
    private val charactersDataSource: CharactersDataSource
) : CharactersRepository {
    override fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        page: Int
    ): Single<CharacterResultEntity> {
        return charactersDataSource.getCharacters(name, status, species, page)
    }

    override fun getCharacter(id: Long): Single<CharacterEntity> {
        return charactersDataSource.getCharacter(id)
    }
}
