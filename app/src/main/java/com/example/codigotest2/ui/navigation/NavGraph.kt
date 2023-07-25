package com.example.codigotest2.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codigotest2.data.local.MovieDatabase
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.ui.view.MovieDetailScreen

import com.example.codigotest2.ui.view.MovieListScreen


@Composable
fun NavGraph(

    startDestination: String = "movie_list_screen") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("movie_list_screen") {
            MovieListScreen( navController = navController)
        }

        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            if (movieId != null) {
                Log.e("movieId", movieId)
            }
            movieId?.let {


                MovieDetailScreen(movieId = movieId, navController = navController)
            }
        }
    }
}