package dev.shtanko.network.api

import dev.shtanko.network.exeption.NetworkConnectionException
import dev.shtanko.network.responses.CharacterResponse
import dev.shtanko.network.responses.CharacterResultResponse
import io.reactivex.Single

interface CharactersApi {

    @Throws(NetworkConnectionException::class)
    fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        page: Int
    ): Single<CharacterResultResponse>

    fun getCharacter(id: Long): Single<CharacterResponse>
}
