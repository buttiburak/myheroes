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
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            fetchCharacters()
        }
    }

    private suspend fun fetchCharacters() {
        _characters.postValue(SimpleResult.loading())

        val characters = repository.fetchCharacters(0)
        characters?.let {
            _characters.postValue(SimpleResult.success(it.toMutableList()))
        } ?: kotlin.run {
            _characters.postValue(SimpleResult.error(""))
        }
    }
}