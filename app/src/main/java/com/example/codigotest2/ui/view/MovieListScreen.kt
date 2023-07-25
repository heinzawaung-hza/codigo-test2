package com.example.codigotest2.ui.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.viewmodel.PopularMoviesViewModel
import com.example.codigotest2.viewmodel.UpcomingMoviesViewModel


@Composable
fun UpcomingMoviesScreen (
    navController: NavController,
    viewModel: UpcomingMoviesViewModel = hiltViewModel()

) {

    val loadingState = viewModel.loadingState.collectAsState()
    val errorState = viewModel.errorState.collectAsState()


    val upcomingMovies = viewModel.upcomingMovies.collectAsState()


    if (loadingState.value) {
        LoadingScreen()
    } else if (errorState.value) {
        ErrorScreen()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
        ) {
            items(upcomingMovies.value.size) { index ->
                MovieListItem(movie = upcomingMovies.value[index]) { movie ->
                    navController.navigate("movieDetail/${movie.id}")
                }
            }
        }

    }
}



@Composable
fun PopularMoviesScreen(
    navController: NavController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {

    val loadingState = viewModel.loadingState.collectAsState()
    val errorState = viewModel.errorState.collectAsState()

    val popularMovies = viewModel.popularMovies.collectAsState()

    if (loadingState.value) {

        LoadingScreen()
    } else if (errorState.value) {
        ErrorScreen()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
        ) {
            items(popularMovies.value.size) { index ->
                MovieListItem(movie = popularMovies.value[index]) { movie ->

                    navController.navigate("movieDetail/${movie.id}")
                }
            }
        }

    }
}

@Preview
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(36.dp),
            color = Color.Black
        )
    }

}

@Preview
@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Error",
                modifier = Modifier.size(48.dp),
                tint = Color.Red
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "An error occurred.",
                style = MaterialTheme.typography.displaySmall,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MovieListScreen(
    navController: NavController,
) {

    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Upcoming", "Popular")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> UpcomingMoviesScreen(navController = navController)
            1 -> PopularMoviesScreen(navController = navController)
        }
    }


}