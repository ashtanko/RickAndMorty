package dev.shtanko.domain.model

data class CharacterResultEntity(
    val info: CharacterInfo,
    val results: List<CharacterEntity>
)
