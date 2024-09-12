package com.daniellapaiva.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniellapaiva.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            val movies = getPopularMoviesUseCase()
            _popularMovies.value = movies
        }
    }

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails: StateFlow<Movie?> = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val movie = getMovieDetailsUseCase(movieId)
            _movieDetails.value = movie
        }
    }
}