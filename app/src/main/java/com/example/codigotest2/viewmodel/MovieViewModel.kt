package com.example.codigotest2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codigotest2.data.local.MovieDatabase
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _upcomingMovies = MutableLiveData<List<Movie>>()

    val upcomingMovies: LiveData<List<Movie>>
        get() = _upcomingMovies

    init {
        fetchAndSaveUpcomingMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYjIzOGMwN2Y0M2Q2ZWNiNmNkMjMyZjA4YmE3NTU4ZSIsInN1YiI6IjY0YmQwNDg1YWM2Yzc5MDhkZTVmN2E2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hE3BXEIvP22vjEd6Frv5BGTsoOLCLsXQ8M6zS9siaic")
        loadUpcomingMoviesFromDb()
    }

    private fun fetchAndSaveUpcomingMovies(apiKey: String) {
        viewModelScope.launch {
            try {
                val movies = withContext(Dispatchers.IO) {
                    repository.fetchAndSaveUpcomingMovies(apiKey)
                }
                _upcomingMovies.value = movies
            } catch (e: IOException) {
                // Handle network error
            }
        }
    }

    private fun loadUpcomingMoviesFromDb() {
        viewModelScope.launch {
            try {
                repository.getUpcomingMoviesOffline().collect { movies ->
                    _upcomingMovies.value = movies
                }
            } catch (e: IOException) {
                // Handle database access error
            }
        }
    }
}