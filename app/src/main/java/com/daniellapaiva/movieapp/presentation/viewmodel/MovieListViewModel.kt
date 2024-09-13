package com.daniellapaiva.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : BaseViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            setLoading(true)
            val movies = getPopularMoviesUseCase()
            _popularMovies.value = movies
            setLoading(false)
        }
    }
}