package com.daniellapaiva.movieapp.domain.usecase

import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.util.safeCall

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): Result<Movie> {
        return safeCall {
            repository.getMovieDetails(movieId)
        }
    }
}