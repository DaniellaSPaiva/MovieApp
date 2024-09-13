package com.daniellapaiva.movieapp.data.repository

import com.daniellapaiva.movieapp.data.remote.api.MovieApiService
import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.model.Movie


const val apiKey = "cd794ff0a85f38b0afd4a1afabb5a066"

class MovieRepositoryImpl(
    private val apiService: MovieApiService,
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return apiService.getPopularMovies(apiKey = apiKey, page = 1).results.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterPath = movie.poster_path
            )
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        val movieDetails = apiService.getMovieDetails(movieId, apiKey = apiKey)
        return Movie(
            id = movieDetails.id,
            title = movieDetails.title,
            overview = movieDetails.overview,
            posterPath = movieDetails.poster_path
        )
    }
}