package dev.shtanko.domain.interactor

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import dev.shtanko.domain.executor.ExecuteSchedulers
import dev.shtanko.domain.repository.CharactersRepository
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    private lateinit var testee: GetCharactersUseCase

    private val charactersRepository: CharactersRepository = mock()
    private val executeSchedulers: ExecuteSchedulers = mock()

    @Before
    fun setUp() {
        testee = GetCharactersUseCase(charactersRepository, executeSchedulers)
    }

    @Test
    fun `get characters test`() {
        val name = "Jake"
        val status = "Alive"
        val species = "Human"
        val page = 1
        val params = GetCharactersUseCase.Params(name, status, species, page)
        testee.buildUseCase(params)
        verify(charactersRepository).getCharacters(name, status, species, page)
        verifyNoMoreInteractions(charactersRepository)
        verifyZeroInteractions(executeSchedulers)
    }
}
