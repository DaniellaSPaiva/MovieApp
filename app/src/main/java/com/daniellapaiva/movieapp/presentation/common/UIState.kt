package com.daniellapaiva.movieapp.presentation.common

sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val error: Throwable) : UIState<Nothing>()
}