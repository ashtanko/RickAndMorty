package dev.shtanko.feature.filter

import dev.shtanko.domain.model.Filter

fun Triple<FilterItem, FilterItem, FilterItem>.toFilter(): Filter {
    val filterByName = first.isEnabled to first.text
    val filterByStatus = second.isEnabled to second.text
    val filterBySpecies = third.isEnabled to third.text

    return Filter(
        filterByName = filterByName,
        filterByStatus = filterByStatus,
        filterBySpecies = filterBySpecies
    )
}
