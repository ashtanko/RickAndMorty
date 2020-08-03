package dev.shtanko.domain.interactor

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.model.Filter
import dev.shtanko.domain.repository.FilterRepository
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test

class SaveFilterUseCaseTest {
    private lateinit var testee: SaveFilterUseCase

    private val filterRepository: FilterRepository = mock()
    private val executeSchedulers: ExecuteSchedulers = mock()

    @Before
    fun setUp() {
        testee = SaveFilterUseCase(filterRepository, executeSchedulers)
        given(executeSchedulers.io()).willReturn(TestScheduler())
        given(executeSchedulers.main()).willReturn(TestScheduler())
    }

    @After
    fun after() {
        testee.dispose()
    }

    @Test
    fun `observe filter test`() {
        val fakeFilter = Filter.empty()
        val params = SaveFilterUseCase.Params(fakeFilter)
        testee.buildCompletable(params)
        verify(filterRepository).saveFilter(fakeFilter)
        verifyNoMoreInteractions(filterRepository)
        verifyZeroInteractions(executeSchedulers)
    }
}
