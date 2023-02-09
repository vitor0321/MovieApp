package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.MoviesRow
import com.example.movieapp.composables.TopBar
import com.example.movieapp.navigation.MovieAppScreen

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { TopBar(name = "Home") })
    {
        it.calculateTopPadding()
        HomeContent(
            navController = navController,
            listMovies = listOf(
                "Avatar",
                "300",
                "Harry Potter",
                "Life",
                "Avatar",
                "300",
                "Harry Potter",
                "Life"
            )
        )
    }
}

@Composable
fun HomeContent(
    navController: NavController,
    listMovies: List<String>
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = listMovies) { movie ->
                MoviesRow(movie = movie) { movie ->
                    navController.navigate(MovieAppScreen.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}