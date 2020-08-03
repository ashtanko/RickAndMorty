package dev.shtanko.domain.interactor

import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.interactor.common.SingleUseCase
import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.domain.repository.CharactersRepository
import io.reactivex.Single

class GetDetailsUseCase(
    private val repository: CharactersRepository,
    executeSchedulers: ExecuteSchedulers
) : SingleUseCase<CharacterEntity, GetDetailsUseCase.Params>(executeSchedulers) {

    data class Params(
        val id: Long
    )

    override fun buildUseCase(params: Params): Single<CharacterEntity> {
        return repository.getCharacter(params.id)
    }
}
