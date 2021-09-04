package com.burak.myheroes.data

import com.squareup.moshi.Json


/**
 * Created by mburak on 4.09.2021.
 */
data class Comic(@field:Json(name = "id") val id: Int,
                 @field:Json(name = "title") val title: String)