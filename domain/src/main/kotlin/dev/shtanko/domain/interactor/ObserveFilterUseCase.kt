package dev.shtanko.domain.interactor

import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.interactor.common.ObservableUseCase
import dev.shtanko.domain.model.Filter
import dev.shtanko.domain.repository.FilterRepository
import io.reactivex.Observable

class ObserveFilterUseCase(
    private val repository: FilterRepository,
    executeSchedulers: ExecuteSchedulers
) : ObservableUseCase<Filter, ObserveFilterUseCase.None>(executeSchedulers) {

    object None

    override fun buildUseCase(params: None): Observable<Filter> {
        return repository.getFilter()
    }
}
