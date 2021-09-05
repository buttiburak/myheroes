package com.burak.myheroes.network

import com.burak.myheroes.data.MarvelCharacter
import com.burak.myheroes.util.AuthenticationUtil
import javax.inject.Inject


/**
 * Created by mburak on 4.09.2021.
 */
class Repository @Inject constructor(private val apiService: ApiService) {
    private var lastOffset = 0
    private val pageCount = 30
    suspend fun fetchCharacters(loadMore: Boolean): List<MarvelCharacter>? {
        if (!loadMore) {
            lastOffset = 0
        }
        val timestamp = System.currentTimeMillis()
        val response = apiService.getCharacters(offset = lastOffset, ts = timestamp,
            hash = AuthenticationUtil.getMd5Digest(timestamp))
        if (response.isSuccessful) {
            val result = response.body()
            result?.let {
                if (it.code == 200 && it.status == "Ok") {
                    lastOffset += pageCount
                    return it.data.results
                }
            }
        }
        return null
    }
}