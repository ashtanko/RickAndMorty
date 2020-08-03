package dev.shtanko.network.responses

data class CharacterResultResponse(
    val info: CharacterInfoResponse,
    val results: List<CharacterResponse>
)
