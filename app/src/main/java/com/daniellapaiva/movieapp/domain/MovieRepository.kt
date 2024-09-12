package com.daniellapaiva.movieapp.domain

import com.daniellapaiva.movieapp.domain.model.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>
    suspend fun getMovieDetails(movieId: Int): Movie
}