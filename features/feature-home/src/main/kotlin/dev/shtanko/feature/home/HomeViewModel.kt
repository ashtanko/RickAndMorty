package dev.shtanko.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import androidx.paging.toLiveData
import dev.shtanko.common.ui.livedata.SingleLiveEvent
import dev.shtanko.domain.interactor.GetCharactersUseCase
import dev.shtanko.domain.interactor.ObserveFilterUseCase
import dev.shtanko.domain.model.Filter
import dev.shtanko.domain.model.NetworkState
import dev.shtanko.feature.home.model.CharacterModel
import dev.shtanko.feature.home.model.CharacterModelMapper
import dev.shtanko.feature.home.paging.CharactersDataSourceFactory
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val observeFilterUseCase: ObserveFilterUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val mapper: CharacterModelMapper
) : ViewModel() {

    companion object {
        private const val PAGE_MAX_ELEMENTS = 50
    }

    private var filterLiveData: MutableLiveData<Filter> = MutableLiveData()

    private val networkState: LiveData<NetworkState>
    private val filteredResult: LiveData<Listing<CharacterModel>>

    val data: LiveData<PagedList<CharacterModel>>
    val event = SingleLiveEvent<HomeViewEvent>()

    init {
        filteredResult = filterLiveData.map {
            getWithFilter(it)
        }

        data = filteredResult.switchMap {
            it.pagedList
        }

        networkState = filteredResult.switchMap {
            it.networkState
        }
    }

    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    HomeViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    HomeViewState.Empty
                } else {
                    HomeViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    HomeViewState.AddLoading
                } else {
                    HomeViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    HomeViewState.AddError
                } else {
                    HomeViewState.Error
                }
        }
    }

    fun observeFilters() {
        observeFilterUseCase.execute(getFilterDisposableObserver(), ObserveFilterUseCase.None)
    }

    fun refreshLoadedCharactersList() {
        filteredResult.value?.refresh?.invoke()
    }

    fun retry() {
        filteredResult.value?.retry?.invoke()
    }

    fun openCharacterDetail(characterId: Long, title: String) {
        event.postValue(HomeViewEvent.OpenCharacterDetail(characterId, title))
    }

    private lateinit var sourceFactory: CharactersDataSourceFactory

    private fun getWithFilter(filter: Filter): Listing<CharacterModel> {
        sourceFactory = CharactersDataSourceFactory(getCharactersUseCase, mapper, filter)
        val livePagedList = sourceFactory.toLiveData(pageSize = PAGE_MAX_ELEMENTS)
        return Listing(
            pagedList = livePagedList,
            networkState = sourceFactory.sourceLiveData.switchMap {
                it.networkState
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retry()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            dispose = {
                sourceFactory.sourceLiveData.value?.dispose()
            }
        )
    }

    private fun getFilterDisposableObserver(): DisposableObserver<Filter> {
        return object : DisposableObserver<Filter>() {
            override fun onComplete() {
                // TODO
            }

            override fun onNext(filter: Filter) {
                filterLiveData.postValue(filter)
            }

            override fun onError(e: Throwable) {
                Timber.e(e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        filteredResult.value?.dispose?.invoke()
        observeFilterUseCase.dispose()
    }
}
