package com.burak.myheroes.data.helper


/**
 * Created by mburak on 4.09.2021.
 */
sealed class SimpleResult<out T> {
    companion object {

        fun <T> success(data: T) = Success(data)

        fun <T> error(msg: String, data: T? = null) = Error(msg, data)

        fun <T> loading(data: T? = null) = Loading(data)

    }

    data class Success<T>(val data: T) : SimpleResult<T>()
    data class Error<T>(val message: String, val data: T? = null) : SimpleResult<T>()
    data class Loading<T>(val data: T? = null, val loadMore: Boolean = false) : SimpleResult<T>()
}