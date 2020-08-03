package dev.shtanko.domain.interactor.common

import dev.shtanko.domain.executor.ExecuteSchedulers
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T, in Params> constructor(
    private val executeSchedulers: ExecuteSchedulers
) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun buildUseCase(params: Params): Single<T>

    fun execute(observer: DisposableSingleObserver<T>, params: Params) {
        val single = buildUseCase(params)
            .subscribeOn(executeSchedulers.io())
            .observeOn(executeSchedulers.main())
        val disposable = single.subscribeWith(observer)
        addDisposable(disposable)
    }

    fun dispose() {
        if (disposables.isDisposed.not()) {
            disposables.clear()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
