package dev.shtanko.domain.interactor

import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.interactor.common.SingleUseCase
import dev.shtanko.domain.model.CharacterResultEntity
import dev.shtanko.domain.repository.CharactersRepository
import io.reactivex.Single

class GetCharactersUseCase(
    private val repository: CharactersRepository,
    executeSchedulers: ExecuteSchedulers
) : SingleUseCase<CharacterResultEntity, GetCharactersUseCase.Params>(executeSchedulers) {

    data class Params(
        val name: String?,
        val status: String?,
        val species: String?,
        val page: Int
    )

    override fun buildUseCase(params: Params): Single<CharacterResultEntity> {
        return repository.getCharacters(
            params.name,
            params.status,
            params.species,
            params.page
        )
    }
}
