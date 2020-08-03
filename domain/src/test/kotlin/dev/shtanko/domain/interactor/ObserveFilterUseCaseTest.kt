package dev.shtanko.domain.interactor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.repository.FilterRepository
import org.junit.Before
import org.junit.Test

class ObserveFilterUseCaseTest {
    private lateinit var testee: ObserveFilterUseCase

    private val filterRepository: FilterRepository = mock()
    private val executeSchedulers: ExecuteSchedulers = mock()

    @Before
    fun setUp() {
        testee = ObserveFilterUseCase(filterRepository, executeSchedulers)
    }

    @Test
    fun `observe filter test`() {
        val params = ObserveFilterUseCase.None
        testee.buildUseCase(params)
        verify(filterRepository).getFilter()
        verifyNoMoreInteractions(filterRepository)
        verifyZeroInteractions(executeSchedulers)
    }
}
