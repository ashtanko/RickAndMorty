package dev.shtanko.domain.interactor.common

import dev.shtanko.domain.executor.ExecuteSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<T, in Params> constructor(
    private val executeSchedulers: ExecuteSchedulers
) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun buildUseCase(params: Params): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params) {
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
