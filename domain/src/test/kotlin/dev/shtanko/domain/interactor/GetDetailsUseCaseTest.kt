package dev.shtanko.domain.interactor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.repository.CharactersRepository
import org.junit.Before
import org.junit.Test

class GetDetailsUseCaseTest {
    private lateinit var testee: GetDetailsUseCase

    private val charactersRepository: CharactersRepository = mock()
    private val executeSchedulers: ExecuteSchedulers = mock()

    @Before
    fun setUp() {
        testee = GetDetailsUseCase(charactersRepository, executeSchedulers)
    }

    @Test
    fun `get details test`() {
        val id = 4L
        val params = GetDetailsUseCase.Params(id)
        testee.buildUseCase(params)
        verify(charactersRepository).getCharacter(id)
        verifyNoMoreInteractions(charactersRepository)
        verifyZeroInteractions(executeSchedulers)
    }
}
