package dev.shtanko.feature.home.model

import dev.shtanko.data.mapper.Mapper
import dev.shtanko.domain.model.CharacterResultEntity

class CharacterModelMapper : Mapper<CharacterResultEntity, List<CharacterModel>> {
    override fun map(from: CharacterResultEntity): List<CharacterModel> {
        return from.results.map {
            CharacterModel(
                it.id,
                it.name,
                it.status,
                it.species,
                it.image
            )
        }
    }
}
