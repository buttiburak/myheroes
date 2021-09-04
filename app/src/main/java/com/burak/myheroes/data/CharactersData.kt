package com.burak.myheroes.data

import com.squareup.moshi.Json


/**
 * Created by mburak on 4.09.2021.
 */
data class CharactersData<T>(@field:Json(name = "offset") val offset: Int,
                          @field:Json(name = "limit") val limit: Int,
                          @field:Json(name = "total") val total: Int,
                          @field:Json(name = "count") val count: Int,
                          @field:Json(name = "results") val results: List<T>)
