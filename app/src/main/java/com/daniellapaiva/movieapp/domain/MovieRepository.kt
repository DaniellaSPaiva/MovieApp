package com.daniellapaiva.movieapp.domain

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>
}