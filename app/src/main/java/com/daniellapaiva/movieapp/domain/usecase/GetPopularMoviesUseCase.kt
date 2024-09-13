package com.daniellapaiva.movieapp.domain.usecase

import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.util.safeCall

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Result<List<Movie>> {
        return safeCall {
            movieRepository.getPopularMovies()
        }
    }
}