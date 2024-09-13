package com.daniellapaiva.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.daniellapaiva.movieapp.domain.util.AppError
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : BaseViewModel<List<Movie>>() {

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            setLoading()
            val result = getPopularMoviesUseCase()
            result.fold(
                onSuccess = { setSuccess(it) },
                onFailure = { error -> handleError(error as AppError) }
            )
        }
    }
}