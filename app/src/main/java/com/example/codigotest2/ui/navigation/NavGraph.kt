package com.example.codigotest2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codigotest2.ui.view.CreateAccountScreen
import com.example.codigotest2.ui.view.MovieListScreen


@Composable
fun NavGraph(startDestination: String = "welcome_screen") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("welcome_screen") {
            MovieListScreen( navController = navController)
        }
        composable("create_account_screen") {
            CreateAccountScreen(navController = navController)
        }
    }
}