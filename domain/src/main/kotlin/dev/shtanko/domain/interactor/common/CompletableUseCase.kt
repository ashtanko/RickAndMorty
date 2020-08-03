package dev.shtanko.domain.interactor.common

import dev.shtanko.domain.executor.ExecuteSchedulers
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<in Params> internal constructor(
    private val executeSchedulers: ExecuteSchedulers
) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    internal abstract fun buildCompletable(params: Params?): Completable

    fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        val completable = buildCompletable(params)
            .subscribeOn(executeSchedulers.io())
            .observeOn(executeSchedulers.main())
        val disposable = completable.subscribeWith(observer)
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
