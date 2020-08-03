package dev.shtanko.network.api

import android.content.Context
import dev.shtanko.network.responses.CharacterResponse
import dev.shtanko.network.responses.CharacterResultResponse
import dev.shtanko.network.services.CharactersService
import io.reactivex.Single
import javax.inject.Inject

class CharactersNetwork @Inject constructor(
    private val service: CharactersService,
    private val context: Context
) : CharactersApi {

    override fun getCharacters(
        name: String?,
        status: String?,
        species: String?,
        page: Int
    ): Single<CharacterResultResponse> {

        return service.getCharacters(name, status, species, page)

        // return if (context.isOnline()) {
        //     service.getCharacters(name, status, species, page)
        // } else {
        //     throw NetworkConnectionException()
        // }
    }

    override fun getCharacter(id: Long): Single<CharacterResponse> {
        return service.getCharacter(id)
    }
}
