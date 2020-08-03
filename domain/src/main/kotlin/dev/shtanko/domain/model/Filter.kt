package dev.shtanko.domain.model

import java.io.Serializable

data class Filter(
    var filterByName: Pair<Boolean, String> = Pair(false, ""),
    var filterByStatus: Pair<Boolean, String> = Pair(false, ""),
    var filterBySpecies: Pair<Boolean, String> = Pair(false, "")
) : Serializable {

    companion object {
        fun empty(): Filter = Filter()
    }

    fun getName(): String? {
        return if (filterByName.first) {
            filterByName.second
        } else {
            null
        }
    }

    fun getStatus(): String? {
        return if (filterByStatus.first) {
            filterByStatus.second
        } else {
            null
        }
    }

    fun getSpecies(): String? {
        return if (filterBySpecies.first) {
            filterBySpecies.second
        } else {
            null
        }
    }
}
