package dev.shtanko.network.responses

data class CharacterResponse(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)
