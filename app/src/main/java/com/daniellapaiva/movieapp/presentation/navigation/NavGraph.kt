package com.daniellapaiva.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daniellapaiva.movieapp.presentation.navigation.NavRoutes.MOVIE_DETAILS_WITH_ID
import com.daniellapaiva.movieapp.presentation.navigation.NavRoutes.MOVIE_LIST
import com.daniellapaiva.movieapp.presentation.navigation.NavRoutes.movieDetailsRoute
import com.daniellapaiva.movieapp.presentation.ui.MovieDetailsScreen
import com.daniellapaiva.movieapp.presentation.ui.MovieListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MOVIE_LIST) {
        composable(MOVIE_LIST) {
            MovieListScreen(onMovieClick = { movieId ->
                navController.navigate(movieDetailsRoute(movieId))
            })
        }
        composable(
            route = MOVIE_DETAILS_WITH_ID,
            arguments = listOf(navArgument("movieId") { type = androidx.navigation.NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            MovieDetailsScreen(movieId = movieId)
        }
    }
}