package com.daniellapaiva.movieapp.domain.usecase

import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.model.Movie

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): Movie {
        return repository.getMovieDetails(movieId)
    }
}