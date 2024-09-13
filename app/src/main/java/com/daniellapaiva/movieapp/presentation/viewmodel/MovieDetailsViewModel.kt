package com.daniellapaiva.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.daniellapaiva.movieapp.domain.util.AppError
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel<Movie>() {

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            setLoading()
            val result = getMovieDetailsUseCase(movieId)
            result.fold(
                onSuccess = { setSuccess(it) },
                onFailure = { error -> handleError(error as AppError) }
            )
        }
    }
}