package com.daniellapaiva.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.daniellapaiva.movieapp.domain.util.AppError
import com.daniellapaiva.movieapp.presentation.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel<T> : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<T>>(UIState.Loading)
    val uiState: StateFlow<UIState<T>> = _uiState

    protected fun setLoading() {
        _uiState.value = UIState.Loading
    }

    protected fun setSuccess(data: T) {
        _uiState.value = UIState.Success(data)
    }

    protected fun setError(error: Throwable) {
        _uiState.value = UIState.Error(error)
    }

    protected fun handleError(error: AppError) {
        when (error) {
            is AppError.NetworkError -> setError(Throwable("No internet connection"))
            is AppError.TimeoutError -> setError(Throwable("Request timed out"))
            is AppError.UnknownError -> setError(Throwable(error.message ?: "Unknown error occurred"))
        }
    }
}