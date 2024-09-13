package com.daniellapaiva.movieapp.domain.util

sealed class AppError : Throwable() {
    data object NetworkError : AppError()
    data object TimeoutError : AppError()
    data class UnknownError(override val message: String?) : AppError()
}