package com.daniellapaiva.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daniellapaiva.movieapp.presentation.ui.MovieListScreen
import com.daniellapaiva.movieapp.presentation.navigation.NavRoutes.MOVIE_LIST

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MOVIE_LIST) {
        composable(MOVIE_LIST) {
            MovieListScreen()
        }
    }
}