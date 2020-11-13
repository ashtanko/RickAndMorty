package dev.shtanko.feature.filter

import android.widget.CompoundButton
import androidx.lifecycle.ViewModel
import dev.shtanko.common.ui.livedata.SingleLiveEvent
import dev.shtanko.domain.interactor.ObserveFilterUseCase
import dev.shtanko.domain.interactor.SaveFilterUseCase
import dev.shtanko.domain.model.Filter
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val saveFilterUseCase: SaveFilterUseCase,
    private val observeFilterUseCase: ObserveFilterUseCase
) : ViewModel() {

    val nameFilterItem = FilterItem()
    val statusFilterItem = FilterItem()
    val speciesFilterItem = FilterItem()

    val event = SingleLiveEvent<FilterViewEvent>()

    fun listenToFilter() {
        observeFilterUseCase.execute(getFilterDisposableObserver(), ObserveFilterUseCase.None)
    }

    val onFilterByNameCheckedListener = CompoundButton.OnCheckedChangeListener { _, _ ->
        nameFilterItem.notifyChange()
    }

    val onFilterByStatusCheckedListener = CompoundButton.OnCheckedChangeListener { _, _ ->
        statusFilterItem.notifyChange()
    }

    val onFilterBySpeciesCheckedListener = CompoundButton.OnCheckedChangeListener { _, _ ->
        speciesFilterItem.notifyChange()
    }

    fun onTestClick() {
        event.postValue(FilterViewEvent.OpenTest)
    }

    fun applyFilter() {
        val items = Triple(
            nameFilterItem,
            statusFilterItem,
            speciesFilterItem
        )

        val params = SaveFilterUseCase.Params(items.toFilter())
        saveFilterUseCase.execute(getDisposableCompletableObserver(), params)
    }

    private fun getDisposableCompletableObserver(): DisposableCompletableObserver {
        return object : DisposableCompletableObserver() {
            override fun onComplete() {
                event.postValue(FilterViewEvent.ApplyFilter)
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
            }
        }
    }

    private fun getFilterDisposableObserver(): DisposableObserver<Filter> {
        return object : DisposableObserver<Filter>() {
            override fun onComplete() {
                // TODO
            }

            override fun onNext(filter: Filter) {
                nameFilterItem.fromFilter(filter.filterByName)
                statusFilterItem.fromFilter(filter.filterByStatus)
                speciesFilterItem.fromFilter(filter.filterBySpecies)
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        saveFilterUseCase.dispose()
        observeFilterUseCase.dispose()
    }
}
