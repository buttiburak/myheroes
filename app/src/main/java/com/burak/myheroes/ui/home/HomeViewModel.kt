package com.burak.myheroes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burak.myheroes.data.Comic
import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.data.helper.SimpleResult
import com.burak.myheroes.data.helper.SingleLiveEvent
import com.burak.myheroes.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by mburak on 5.09.2021.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val _characters: MutableLiveData<SimpleResult<MutableList<MarvelCharacter>>> = MutableLiveData()
    val characters: LiveData<SimpleResult<MutableList<MarvelCharacter>>> = _characters

    private val _selectedCharacters: MutableLiveData<MarvelCharacter> = MutableLiveData()
    val selectedCharacters: LiveData<MarvelCharacter> = _selectedCharacters

    private val _selectedPosition: SingleLiveEvent<Int> = SingleLiveEvent()
    val selectedPosition: LiveData<Int> = _selectedPosition

    private val _comics: MutableLiveData<SimpleResult<MutableList<Comic>>> = MutableLiveData()
    val comics: LiveData<SimpleResult<MutableList<Comic>>> = _comics

    init {
        getCharacters(loadMore = false)
    }

    fun getCharacters(loadMore: Boolean) {
        viewModelScope.launch {
            fetchCharacters(loadMore)
        }
    }

    fun getComics(characterId: Int) {
        viewModelScope.launch {
            fetchComics(characterId)
        }
    }

    fun isLoading(): Boolean = _characters.value is SimpleResult.Loading

    fun selectCharacter(character: MarvelCharacter, position: Int) {
        viewModelScope.launch {
            selectCharacterAndSetupCache(character, position)
        }
    }

    private suspend fun selectCharacterAndSetupCache(character: MarvelCharacter, position: Int) {
        _selectedCharacters.postValue(character)
        _selectedPosition.postValue(position)

        val charactersList = repository.fetchCharacters(fromCache = true)
        setCharacterList(charactersList)
    }

    private suspend fun fetchCharacters(loadMore: Boolean) {
        _characters.postValue(SimpleResult.loading(loadMore = loadMore))

        val charactersList = repository.fetchCharacters(loadMore)
        setCharacterList(charactersList)
    }

    private suspend fun fetchComics(characterId: Int) {
        _comics.postValue(SimpleResult.loading())

        val comicsList = repository.fetchComicsOfCharacter(characterId)
        _comics.postValue(SimpleResult.success(comicsList.toMutableList()))
    }

    private fun setCharacterList(charactersList: List<MarvelCharacter>?) {
        charactersList?.let {
            _characters.postValue(SimpleResult.success(it.toMutableList()))
        } ?: kotlin.run {
            _characters.postValue(SimpleResult.error(""))
        }
    }
}