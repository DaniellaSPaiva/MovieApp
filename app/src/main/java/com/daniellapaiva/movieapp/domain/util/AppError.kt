package com.daniellapaiva.movieapp.domain.util

sealed class AppError : Throwable() {

    data object NetworkError : AppError() {
        private fun readResolve(): Any = NetworkError
    }

    data object TimeoutError : AppError() {
        private fun readResolve(): Any = TimeoutError
    }

    data class UnknownError(override val message: String?) : AppError()

}