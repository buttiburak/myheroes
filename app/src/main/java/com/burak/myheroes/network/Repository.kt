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
    private val cachedData = mutableListOf<MarvelCharacter>()

    suspend fun fetchCharacters(loadMore: Boolean = false, fromCache: Boolean = false): List<MarvelCharacter>? {
        if (!loadMore && !fromCache) {
            lastOffset = 0
            cachedData.clear()
        }

        if (fromCache) {
            return cachedData
        }

        val timestamp = System.currentTimeMillis()
        val response = apiService.getCharacters(offset = lastOffset, ts = timestamp,
            hash = AuthenticationUtil.getMd5Digest(timestamp))
        if (response.isSuccessful) {
            val result = response.body()
            result?.let {
                if (it.code == 200 && it.status == "Ok") {
                    lastOffset += pageCount
                    cachedData.addAll(it.data.results)
                    return it.data.results
                }
            }
        }
        return null
    }
}