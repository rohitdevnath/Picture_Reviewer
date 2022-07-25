package com.example.reviewerpicture.utils.networkUtils

sealed class Outcome<T> {
    data class Progress<T>(var loading: Boolean) : Outcome<T>()
    data class Success<T>(var data: T, var silent: Boolean) : Outcome<T>()
    data class Failure<T>(val e: Throwable) : Outcome<T>()

    companion object {
        fun <T> loading(isLoading: Boolean = true): Outcome<T> = Progress<T>(isLoading)

        fun <T> success(data: T, silent: Boolean = false): Outcome<T> = Success<T>( data,silent)

        fun <T> failure(e: Throwable): Outcome<T> = Failure<T>(e)
    }
}