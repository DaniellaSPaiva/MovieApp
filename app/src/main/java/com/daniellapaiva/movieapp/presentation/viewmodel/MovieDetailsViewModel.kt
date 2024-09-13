package com.daniellapaiva.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails: StateFlow<Movie?> = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            setLoading(true)
            val movie = getMovieDetailsUseCase(movieId)
            _movieDetails.value = movie
            setLoading(false)
        }
    }
}