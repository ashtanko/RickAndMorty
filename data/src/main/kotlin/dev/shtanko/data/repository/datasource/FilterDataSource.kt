package dev.shtanko.data.repository.datasource

import dev.shtanko.domain.model.Filter
import io.reactivex.Observable

interface FilterDataSource {

    fun saveFilter(filter: Filter)

    fun getFilter(): Observable<Filter>
}
