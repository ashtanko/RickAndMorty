package dev.shtanko.data.repository

import dev.shtanko.data.repository.datasource.FilterDataSource
import dev.shtanko.domain.model.Filter
import dev.shtanko.domain.repository.FilterRepository
import io.reactivex.Observable
import javax.inject.Inject

class FilterDataRepository @Inject constructor(private val dataSource: FilterDataSource) :
    FilterRepository {
    override fun saveFilter(filter: Filter) {
        dataSource.saveFilter(filter)
    }

    override fun getFilter(): Observable<Filter> {
        return dataSource.getFilter()
    }
}
