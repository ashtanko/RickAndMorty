package dev.shtanko.network.services

import dev.shtanko.network.responses.CharacterResponse
import dev.shtanko.network.responses.CharacterResultResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Representation interface of the Rick and Morty API.
 * @see <a href="https://rickandmortyapi.com/documentation/#rest</a>
 */
interface CharactersService {

    @GET("character")
    fun getCharacters(
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("species") species: String?,
        @Query("page") page: Int
    ): Single<CharacterResultResponse>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Long): Single<CharacterResponse>
}
