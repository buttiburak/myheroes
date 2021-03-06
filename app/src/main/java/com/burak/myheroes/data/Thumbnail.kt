package com.burak.myheroes.data

import com.squareup.moshi.Json


/**
 * Created by mburak on 4.09.2021.
 */
data class Thumbnail(@field:Json(name = "path") val path: String,
                     @field:Json(name = "extension") val extension: String) {
    fun getThumbnailPath(): String = "$path/landscape_xlarge.$extension"
}
