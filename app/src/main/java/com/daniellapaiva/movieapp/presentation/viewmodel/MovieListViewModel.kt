package com.daniellapaiva.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
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
            try {
                setLoading()
                val movies = getPopularMoviesUseCase()
                setSuccess(movies)
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }
}