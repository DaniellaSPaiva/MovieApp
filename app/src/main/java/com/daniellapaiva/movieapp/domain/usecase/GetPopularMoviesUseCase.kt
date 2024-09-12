package com.daniellapaiva.movieapp.domain.usecase

import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.MovieRepository

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getPopularMovies()
    }
}