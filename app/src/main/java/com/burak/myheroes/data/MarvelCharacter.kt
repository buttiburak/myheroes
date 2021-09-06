package com.burak.myheroes.data

import com.squareup.moshi.Json


/**
 * Created by mburak on 4.09.2021.
 */
data class MarvelCharacter(@field:Json(name = "id") val id: Int,
                           @field:Json(name = "name") val name: String,
                           @field:Json(name = "description") val description: String,
                           @field:Json(name = "thumbnail") val thumbnail: Thumbnail)
