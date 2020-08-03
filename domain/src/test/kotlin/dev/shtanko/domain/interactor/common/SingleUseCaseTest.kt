package dev.shtanko.domain.interactor.common

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import dev.shtanko.domain.executor.ExecuteSchedulers
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class SingleUseCaseTest {

    private val executeSchedulers: ExecuteSchedulers = mock()

    private lateinit var testee: UseCaseTestee

    private lateinit var testDisposableObserver: TestDisposableObserver<Any>

    @Before
    fun setUp() {
        testee = UseCaseTestee()
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

    inner class UseCaseTestee : SingleUseCase<Any, Params>(executeSchedulers) {
        override fun buildUseCase(params: Params): Single<Any> {
            return Single.never()
        }
    }

    object Params

    private class TestDisposableObserver<T> : DisposableSingleObserver<T>() {

        var values: Int = 0

        override fun onSuccess(t: T) {
            values++
        }

        override fun onError(e: Throwable) {
        }
    }
}
