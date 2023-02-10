package com.example.movieapp.widgets

import android.widget.ImageView.ScaleType
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Composable
fun MoviesRow(movie: Movie, onClick: (String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                onClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = 6.dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .width(100.dp),
                elevation = 4.dp
            ) {

                Image(
                    modifier = Modifier.aspectRatio(9f / 16f),
                    painter = rememberImagePainter(data = movie.poster.replace("http", "https"),
                        builder = {
                            crossfade(true)
                            // transformations(CircleCropTransformation())
                        }),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Poster of ${movie.title}"
                )
            }

            Column(modifier = Modifier.padding(4.dp)) {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption
                )

                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption
                )

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Light)) {
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))

                        Divider(modifier = Modifier.padding(bottom = 4.dp))

                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating : ${movie.rating}", style = MaterialTheme.typography.caption)
                    }
                }

                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { expanded = !expanded },
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Arrow up" else "Arrow Down",
                    tint = Color.DarkGray
                )
            }
        }
    }
}

@Preview
@Composable
fun MoviesRowPreview() {
    MoviesRow(movie = getMovies()[0]) {

    }
}