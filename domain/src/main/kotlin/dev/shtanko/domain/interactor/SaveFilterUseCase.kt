package dev.shtanko.domain.interactor

import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.interactor.common.CompletableUseCase
import dev.shtanko.domain.model.Filter
import dev.shtanko.domain.repository.FilterRepository
import io.reactivex.Completable

class SaveFilterUseCase(
    private val repository: FilterRepository,
    executeSchedulers: ExecuteSchedulers
) : CompletableUseCase<SaveFilterUseCase.Params>(executeSchedulers) {

    data class Params(val filter: Filter)

    override fun buildCompletable(params: Params?): Completable {
        params?.filter?.let { repository.saveFilter(it) }
        return Completable.complete()
    }
}
