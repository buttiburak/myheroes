package com.burak.myheroes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.data.helper.SimpleResult
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

    init {
        getCharacters(loadMore = false)
    }

    fun getCharacters(loadMore: Boolean) {
        viewModelScope.launch {
            fetchCharacters(loadMore)
        }
    }

    fun isLoading(): Boolean = _characters.value is SimpleResult.Loading

    private suspend fun fetchCharacters(loadMore: Boolean) {
        _characters.postValue(SimpleResult.loading(loadMore = loadMore))

        val characters = repository.fetchCharacters(loadMore)
        characters?.let {
            _characters.postValue(SimpleResult.success(it.toMutableList()))
        } ?: kotlin.run {
            _characters.postValue(SimpleResult.error(""))
        }
    }
}