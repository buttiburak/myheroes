package com.burak.myheroes.network

import com.burak.myheroes.data.CharactersResponse
import com.burak.myheroes.data.Comic
import com.burak.myheroes.data.MarvelCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by mburak on 4.09.2021.
 */
interface ApiService {
    @GET("characters")
    suspend fun getCharacters(@Query("limit") limit: Int = 30,
                              @Query("offset") offset: Int = 0,
                              @Query("apikey") apikey: String = "deec2631188bf94df88017d047a0eccd",):
            Response<CharactersResponse<MarvelCharacter>>

    @GET("characters/{characterId}/comics")
    suspend fun getComicsOfCharacter(@Path("characterId") characterId: Int,
                                     @Query("orderBy") orderBy: String = "-modified",
                                     @Query("limit") limit: Int = 10,
                                     @Query("apikey") apikey: String = "deec2631188bf94df88017d047a0eccd"):
            Response<CharactersResponse<Comic>>

}