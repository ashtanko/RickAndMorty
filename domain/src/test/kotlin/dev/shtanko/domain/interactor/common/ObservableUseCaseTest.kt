package dev.shtanko.domain.interactor.common

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import dev.shtanko.domain.executor.ExecuteSchedulers
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ObservableUseCaseTest {

    private val executeSchedulers: ExecuteSchedulers = mock()

    private lateinit var testee: ObservableUseCaseTestee

    private lateinit var testDisposableObserver: TestDisposableObserver<Any>

    @Before
    fun setUp() {
        testee = ObservableUseCaseTestee()
        testDisposableObserver =
            TestDisposableObserver()
        given(executeSchedulers.io()).willReturn(TestScheduler())
        given(executeSchedulers.main()).willReturn(TestScheduler())
    }

    @After
    fun after() {
        testee.dispose()
    }

    @Test
    fun `test use case return correct result`() {
        testee.execute(testDisposableObserver,
            Params
        )
        assertThat(testDisposableObserver.values).isZero()
    }

    @Test
    fun `test subscription`() {
        testee.execute(testDisposableObserver,
            Params
        )
        assertThat(testDisposableObserver.isDisposed).isFalse()
        testee.dispose()
        assertThat(testDisposableObserver.isDisposed).isTrue()
    }

    inner class ObservableUseCaseTestee : ObservableUseCase<Any, Params>(executeSchedulers) {
        override fun buildUseCase(params: Params): Observable<Any> {
            return Observable.empty()
        }
    }

    object Params

    private class TestDisposableObserver<T> : DisposableObserver<T>() {

        var values: Int = 0

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }

        override fun onNext(t: T) {
            values++
        }
    }
}
