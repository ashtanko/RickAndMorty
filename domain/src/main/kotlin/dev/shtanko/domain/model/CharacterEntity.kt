package dev.shtanko.domain.model

data class CharacterEntity(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)
