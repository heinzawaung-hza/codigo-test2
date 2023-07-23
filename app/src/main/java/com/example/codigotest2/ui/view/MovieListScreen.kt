package com.example.codigotest2.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.codigotest2.viewmodel.CreateAccountViewModel
import com.example.codigotest2.viewmodel.MovieViewModel


@Composable
fun UpcomingScreen(
    viewModel: MovieViewModel = hiltViewModel()
) {

    val upcomingMovies = viewModel.upcomingMovies


}

@Composable
fun PopularScreen() {
    LazyColumn {
        items(10) {
            Text("popular")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: CreateAccountViewModel = hiltViewModel()
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
            0 -> UpcomingScreen()
            1 -> PopularScreen()
        }
    }


}