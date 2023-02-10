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
import com.example.movieapp.widgets.MoviesRow
import com.example.movieapp.widgets.TopBar
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieAppScreen

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { TopBar(name = "Movies") })
    {
        it.calculateTopPadding()
        HomeContent(
            navController = navController,
            listMovies = getMovies()
        )
    }
}

@Composable
fun HomeContent(
    navController: NavController,
    listMovies: List<Movie>
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = listMovies) {
                MoviesRow(movie = it) { movie ->
                    navController.navigate(MovieAppScreen.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}