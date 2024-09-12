package com.daniellapaiva.movieapp.data.repository

import com.daniellapaiva.movieapp.data.MovieApiService
import com.daniellapaiva.movieapp.domain.model.Movie
import com.daniellapaiva.movieapp.domain.MovieRepository
import com.daniellapaiva.movieapp.domain.util.LanguageService


const val apiKey = "cd794ff0a85f38b0afd4a1afabb5a066"

class MovieRepositoryImpl(
    private val apiService: MovieApiService,
    private val languageService: LanguageService
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        val language = languageService.getLanguageForApi()
        return apiService.getPopularMovies(apiKey = apiKey, page = 1, language = language).results.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterPath = movie.poster_path
            )
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        val language = languageService.getLanguageForApi()
        val movieDetails = apiService.getMovieDetails(movieId, apiKey = apiKey, language = language)
        return Movie(
            id = movieDetails.id,
            title = movieDetails.title,
            overview = movieDetails.overview,
            posterPath = movieDetails.poster_path
        )
    }
}