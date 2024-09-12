package com.daniellapaiva.movieapp.data.model

data class MovieListResponse(
    val results: List<MovieDetailResponse>
)

data class MovieDetailResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String
)
