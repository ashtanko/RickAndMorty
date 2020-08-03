package dev.shtanko.data.mapper

import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.network.responses.CharacterResultResponse

class CharactersMapper : Mapper<CharacterResultResponse, List<CharacterEntity>> {
    override fun map(from: CharacterResultResponse): List<CharacterEntity> {
        return from.results.map {
            CharacterEntity(
                it.id,
                it.name,
                it.status,
                it.species,
                it.image
            )
        }
    }
}
