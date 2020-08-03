package dev.shtanko.data.repository.datasource.remote

import dev.shtanko.data.mapper.CharacterMapper
import dev.shtanko.data.mapper.CharactersMapper
import dev.shtanko.data.repository.datasource.CharactersDataSource
import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.domain.model.CharacterInfo
import dev.shtanko.domain.model.CharacterResultEntity
import dev.shtanko.network.api.CharactersApi
import io.reactivex.Single
import javax.inject.Inject

class CharactersRemoteDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val listMapper: CharactersMapper,
    private val entityMapper: CharacterMapper
) : CharactersDataSource {

    override fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        page: Int
    ): Single<CharacterResultEntity> {
        return charactersApi.getCharacters(name, status, species, page).map { it ->
            CharacterResultEntity(
                CharacterInfo(it.info.pages),
                listMapper.map(it)
            )
        }
    }

    override fun getCharacter(id: Long): Single<CharacterEntity> {
        return charactersApi.getCharacter(id).map {
            entityMapper.map(it)
        }
    }
}
