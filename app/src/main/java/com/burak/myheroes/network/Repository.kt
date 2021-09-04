package com.burak.myheroes.network

import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.util.AuthenticationUtil
import javax.inject.Inject


/**
 * Created by mburak on 4.09.2021.
 */
class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchCharacters(offset: Int): List<MarvelCharacter>? {
        val timestamp = System.currentTimeMillis()
        val response = apiService.getCharacters(offset = offset, ts = timestamp,
            hash = AuthenticationUtil.getMd5Digest(timestamp))
        if (response.isSuccessful) {
            val result = response.body()
            result?.let {
                if (it.code == 200 && it.status == "Ok") {
                    return it.data.results
                }
            }
        }
        return null
    }
}