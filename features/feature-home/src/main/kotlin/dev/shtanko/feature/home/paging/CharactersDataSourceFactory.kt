package dev.shtanko.feature.home.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.shtanko.domain.interactor.GetCharactersUseCase
import dev.shtanko.domain.model.Filter
import dev.shtanko.feature.home.model.CharacterModel
import dev.shtanko.feature.home.model.CharacterModelMapper

class CharactersDataSourceFactory(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val mapper: CharacterModelMapper,
    private val filter: Filter
) : DataSource.Factory<Int, CharacterModel>() {

    val sourceLiveData = MutableLiveData<CharactersPagingDataSource>()

    override fun create(): DataSource<Int, CharacterModel> {
        val dataSource = CharactersPagingDataSource(getCharactersUseCase, mapper, filter)
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    fun refresh() {
        sourceLiveData.value?.invalidate()
    }

    /**
     * Force retry last fetch operation on data source.
     */
    fun retry() {
        sourceLiveData.value?.retry()
    }
}
