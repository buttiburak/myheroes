package com.burak.myheroes.data

import com.squareup.moshi.Json


/**
 * Created by mburak on 4.09.2021.
 */
data class CharactersResponse<T>(@field:Json(name = "code") val code: Int,
                              @field:Json(name = "status") val status: String,
                              @field:Json(name = "copyright") val copyright: String,
                              @field:Json(name = "attributionText") val attributionText: String,
                              @field:Json(name = "data") val data: CharactersData<T>)
