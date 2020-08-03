package dev.shtanko.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.shtanko.domain.interactor.GetDetailsUseCase
import dev.shtanko.domain.model.CharacterEntity
import dev.shtanko.feature.detail.model.CharacterDetail
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _data = MutableLiveData<CharacterDetail>()
    val data: LiveData<CharacterDetail>
        get() = _data

    private val _state = MutableLiveData<CharacterDetailViewState>()
    val state: LiveData<CharacterDetailViewState>
        get() = _state

    fun loadCharacterDetail(characterId: Long) {
        _state.postValue(CharacterDetailViewState.Loading)

        getDetailsUseCase.execute(
            getDisposableSingleObserver(),
            GetDetailsUseCase.Params(characterId)
        )
    }

    private fun getDisposableSingleObserver(): DisposableSingleObserver<CharacterEntity> {
        return object : DisposableSingleObserver<CharacterEntity>() {
            override fun onSuccess(t: CharacterEntity) {
                val characterDetail = CharacterDetail(t.id, t.name, t.status, t.species, t.image)
                _data.postValue(characterDetail)
                _state.postValue(CharacterDetailViewState.Loaded)
            }

            override fun onError(e: Throwable) {
                _state.postValue(CharacterDetailViewState.Error)
            }
        }
    }
}
