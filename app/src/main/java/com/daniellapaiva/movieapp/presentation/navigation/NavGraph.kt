package com.daniellapaiva.movieapp.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daniellapaiva.movieapp.R
import com.daniellapaiva.movieapp.presentation.navigation.NavRoutes.MOVIE_DETAILS_WITH_ID
import com.daniellapaiva.movieapp.presentation.navigation.NavRoutes.MOVIE_ID_ARG
import com.daniellapaiva.movieapp.presentation.navigation.NavRoutes.MOVIE_LIST
import com.daniellapaiva.movieapp.presentation.navigation.NavRoutes.movieDetailsRoute
import com.daniellapaiva.movieapp.presentation.ui.MovieDetailsScreen
import com.daniellapaiva.movieapp.presentation.ui.MovieListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MOVIE_LIST) {
        composable(MOVIE_LIST) {
            MovieListComposable(navController)
        }

        composable(
            route = MOVIE_DETAILS_WITH_ID,
            arguments = listOf(navArgument(MOVIE_ID_ARG) { type = NavType.IntType })
        ) { backStackEntry ->
            MovieDetailsComposable(navController, backStackEntry)
        }
    }
}

@Composable
fun MovieListComposable(navController: NavHostController) {
    MovieListScreen(onNavigateToDetails = { movieId ->
        navController.navigate(movieDetailsRoute(movieId))
    })
}

@Composable
fun MovieDetailsComposable(navController: NavHostController, backStackEntry: NavBackStackEntry) {
    val movieId = backStackEntry.arguments?.getInt(MOVIE_ID_ARG)
    movieId?.let {
        MovieDetailsScreen(movieId = it)
    } ?: run {
        Toast.makeText(
            navController.context,
            stringResource(R.string.nav_graph_invalid_movie_id),
            Toast.LENGTH_SHORT
        ).show()
    }
}