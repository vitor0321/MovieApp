package com.example.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MoviesRow
import com.example.movieapp.widgets.TopBar

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    val movie = getMovies().find { movie ->
        movie.id == movieId
    }

    Scaffold(topBar = {
        TopBar(
            name = "Details",
            navController = navController,
            imageVector = Icons.Default.ArrowBack
        )
    }) {
        it.calculateTopPadding()
        DetailsContent(movie = movie)
    }
}

@Composable
fun DetailsContent(movie: Movie?) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            movie?.let {
                MoviesRow(movie = movie) {}

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = "Movie images",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.size(8.dp))

                Divider()

                Spacer(modifier = Modifier.size(8.dp))

                HorizontalScrollableImageView(movie)
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->

            Card(
                modifier = Modifier
                    .padding(6.dp)
                    .height(200.dp),
                elevation = 5.dp
            ) {
                Image(
                    modifier = Modifier.aspectRatio(16f / 9f),
                    painter = rememberImagePainter(data = image),
                    contentScale = ContentScale.Crop,
                    contentDescription = "image by ${movie.title} movie"
                )
            }
        }
    }
}