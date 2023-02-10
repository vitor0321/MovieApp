package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.details.DetailsScreen
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieAppScreen.HomeScreen.name
    ) {
        composable(
            route = MovieAppScreen.HomeScreen.name
        ) {
            HomeScreen(navController = navController)
        }

        //www.google.com/details-screen/movie=name
        composable(
            route = MovieAppScreen.DetailsScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movie")
            )
        }
    }
}