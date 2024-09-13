package com.daniellapaiva.movieapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel<Movie>() {

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                setLoading()
                val movies = getMovieDetailsUseCase(movieId)
                setSuccess(movies)
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }
}