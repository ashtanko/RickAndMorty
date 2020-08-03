package dev.shtanko.feature.home.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import dev.shtanko.domain.interactor.GetCharactersUseCase
import dev.shtanko.domain.model.Filter
import dev.shtanko.domain.model.NetworkState
import dev.shtanko.feature.home.model.CharacterModel
import dev.shtanko.feature.home.model.CharacterModelMapper
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class CharactersPagingDataSource constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val mapper: CharacterModelMapper,
    private val filter: Filter
) : PageKeyedDataSource<Int, CharacterModel>() {

    val networkState = MutableLiveData<NetworkState>()
    private var retry: (() -> Unit)? = null

    private val compositeDisposables = CompositeDisposable()

    fun dispose() {
        compositeDisposables.clear()
    }

    fun retry() {
        retry?.invoke()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterModel>
    ) {
        networkState.postValue(NetworkState.Loading())

        val name = filter.getName()
        val status = filter.getStatus()
        val species = filter.getSpecies()

        val useCaseParams = GetCharactersUseCase.Params(name, status, species, 0)

        val disposable =
            getCharactersUseCase.buildUseCase(useCaseParams)
                .subscribe({
                    val data = mapper.map(it)
                    callback.onResult(data, null, 1)
                    networkState.postValue(NetworkState.Success(isEmptyResponse = data.isEmpty()))
                }, {
                    retry = {
                        loadInitial(params, callback)
                    }
                    networkState.postValue(NetworkState.Error())
                })
        compositeDisposables.add(disposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterModel>) {
        networkState.postValue(NetworkState.Loading(true))

        val name = filter.getName()
        val status = filter.getStatus()
        val species = filter.getSpecies()

        val disposable =
            getCharactersUseCase.buildUseCase(
                GetCharactersUseCase.Params(
                    name,
                    status,
                    species,
                    params.key
                )
            ).subscribe({
                val data = mapper.map(it)
                callback.onResult(data, params.key + 1)
                networkState.postValue(NetworkState.Success(true, data.isEmpty()))
            }, {
                retry = {
                    loadAfter(params, callback)
                }
                Timber.e(it)
                networkState.postValue(NetworkState.Error(true))
            })

        compositeDisposables.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterModel>) {
        // ignore
    }
}
