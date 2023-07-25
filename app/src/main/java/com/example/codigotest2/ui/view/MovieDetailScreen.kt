package com.example.codigotest2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.codigotest2.R
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.util.Constants
import com.example.codigotest2.viewmodel.MovieDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    movieId: String
) {

    val movie: Movie = viewModel.getMovieById(movieId.toInt()).collectAsState(null).value
        ?:
        return

    val isMovieSaved = remember { mutableStateOf(true) }
    isMovieSaved.value = movie.favourite == 1


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Top AppBar
        TopAppBar(
            title = { Text(text = movie.title ?: "Movie Detail") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }

            },
            actions = {
                // Show the Save icon in the AppBar and handle its click event
                IconButton(onClick = {
                    if (!isMovieSaved.value) {
                        movie.favourite = 1
                        viewModel.saveMovie(movie)
                        isMovieSaved.value = true
                    }else {
                        movie.favourite = 0
                        viewModel.saveMovie(movie)
                        isMovieSaved.value = false
                    }
                }) {
                    val icon = if (isMovieSaved.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                    val iconTint = if (isMovieSaved.value) Color.Red else Color.Unspecified
                    Icon(icon, contentDescription = "Favorite", tint = iconTint)
                }
            }
        )

        // Movie poster
        Image(
            painter = rememberImagePainter(data = Constants.IMAGE_URL + movie.posterPath),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "Title: ${movie.title ?: "Unknown"}", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Release Date: ${movie.releaseDate ?: "Unknown"}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Overview:", style = MaterialTheme.typography.bodyMedium)
        Text(text = movie.overview ?: "No overview available", style = MaterialTheme.typography.bodyMedium)
    }
}

