package com.example.codigotest2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> get() = _popularMovies

    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _errorState = MutableStateFlow<Boolean>(false)
    val errorState: StateFlow<Boolean> get() = _errorState

    init {
        loadPopularMoviesFromDb()
        fetchAndSavePopularMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYjIzOGMwN2Y0M2Q2ZWNiNmNkMjMyZjA4YmE3NTU4ZSIsInN1YiI6IjY0YmQwNDg1YWM2Yzc5MDhkZTVmN2E2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hE3BXEIvP22vjEd6Frv5BGTsoOLCLsXQ8M6zS9siaic")
    }

    private fun fetchAndSavePopularMovies(apiKey: String) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                val movies = withContext(Dispatchers.IO) {
                    repository.fetchAndSavePopularMovies(apiKey)
                }
                _popularMovies.value = movies
            } catch (e: IOException) {
                _errorState.value = true
            } finally {
                _loadingState.value = false
            }
        }
    }

    private fun loadPopularMoviesFromDb() {
        viewModelScope.launch {
            try {
                repository.getPopularMoviesOffline().collect { movies ->
                    _popularMovies.value = movies
                }
            } catch (e: IOException) {
                _errorState.value = true
            } finally {
                _loadingState.value = false
            }
        }
    }
}