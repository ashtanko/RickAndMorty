package dev.shtanko.feature.filter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import dev.shtanko.domain.interactor.ObserveFilterUseCase
import dev.shtanko.domain.interactor.SaveFilterUseCase
import dev.shtanko.domain.model.Filter
import io.reactivex.observers.DisposableCompletableObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FilterViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val saveFilterUseCase: SaveFilterUseCase = mock()
    private val observeFilterUseCase: ObserveFilterUseCase = mock()

    private val eventObserver: Observer<FilterViewEvent> = mock()

    private lateinit var testee: FilterViewModel
    private lateinit var completableCaptor: KArgumentCaptor<DisposableCompletableObserver>

    @Before
    fun setUp() {
        completableCaptor = argumentCaptor()

        testee = FilterViewModel(saveFilterUseCase, observeFilterUseCase)
        testee.event.observeForever(eventObserver)
    }

    @Test
    fun `save filter should set apply filter event`() {
        testee.applyFilter()
        val params = SaveFilterUseCase.Params(Filter.empty())
        verify(saveFilterUseCase).execute(completableCaptor.capture(), eq(params))
        completableCaptor.firstValue.onComplete()
        verify(eventObserver).onChanged(FilterViewEvent.ApplyFilter)
    }
}
