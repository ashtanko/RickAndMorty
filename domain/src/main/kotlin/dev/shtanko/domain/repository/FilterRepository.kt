package dev.shtanko.domain.repository

import dev.shtanko.domain.model.Filter
import io.reactivex.Observable

interface FilterRepository {
    fun saveFilter(filter: Filter)

    fun getFilter(): Observable<Filter>
}
