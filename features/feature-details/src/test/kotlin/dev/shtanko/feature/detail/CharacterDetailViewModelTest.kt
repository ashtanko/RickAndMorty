package dev.shtanko.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import dev.shtanko.domain.interactor.GetDetailsUseCase
import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.feature.detail.model.CharacterDetail
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getDetailsUseCase: GetDetailsUseCase = mock()

    private val stateObserver: Observer<CharacterDetailViewState> = mock()
    private val dataObserver: Observer<CharacterDetail> = mock()

    private lateinit var testee: CharacterDetailViewModel
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<CharacterEntity>>

    @Before
    fun setUp() {
        captor = argumentCaptor()

        testee = CharacterDetailViewModel(getDetailsUseCase)
        testee.state.observeForever(stateObserver)
        testee.data.observeForever(dataObserver)
    }

    @Test
    fun `load character detail should set loading state`() {
        testee.loadCharacterDetail(1L)
        verify(getDetailsUseCase).execute(captor.capture(), eq(GetDetailsUseCase.Params(1)))
        captor.firstValue.onSuccess(getFakeCharacterEntity())
        verify(stateObserver).onChanged(CharacterDetailViewState.Loading)
    }

    @Test
    fun `load character detail should set loaded state`() {
        testee.loadCharacterDetail(1L)
        verify(getDetailsUseCase).execute(captor.capture(), eq(GetDetailsUseCase.Params(1)))
        captor.firstValue.onSuccess(getFakeCharacterEntity())
        verify(stateObserver).onChanged(CharacterDetailViewState.Loaded)
    }

    @Test
    fun `load character detail network error should set error state`() {
        testee.loadCharacterDetail(1L)
        verify(getDetailsUseCase).execute(captor.capture(), eq(GetDetailsUseCase.Params(1)))
        captor.firstValue.onError(RuntimeException())
        verify(stateObserver).onChanged(CharacterDetailViewState.Error)
    }

    private fun getFakeCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            1,
            "fakeName",
            "fakeStatus",
            "fakeSpecies",
            "fake.png"
        )
    }
}
