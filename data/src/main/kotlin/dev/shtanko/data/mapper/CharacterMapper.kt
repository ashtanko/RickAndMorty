package dev.shtanko.data.mapper

import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.network.responses.CharacterResponse

class CharacterMapper : Mapper<CharacterResponse, CharacterEntity> {

    override fun map(from: CharacterResponse): CharacterEntity {
        return CharacterEntity(
            from.id,
            from.name,
            from.status,
            from.species,
            from.image
        )
    }
}
