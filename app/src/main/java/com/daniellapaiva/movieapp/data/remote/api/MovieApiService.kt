package com.daniellapaiva.movieapp.data.remote.api

import com.daniellapaiva.movieapp.data.model.MovieDetailResponse
import com.daniellapaiva.movieapp.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieDetailResponse
}