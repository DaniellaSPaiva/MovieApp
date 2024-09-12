package com.daniellapaiva.movieapp.presentation.navigation

object NavRoutes {
    const val MOVIE_LIST = "movie_list"
    private const val MOVIE_DETAILS = "movie_details"

    const val MOVIE_DETAILS_WITH_ID = "$MOVIE_DETAILS/{movieId}"
    fun movieDetailsRoute(movieId: Int): String = "$MOVIE_DETAILS/$movieId"
}