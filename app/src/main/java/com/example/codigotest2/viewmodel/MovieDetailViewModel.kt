package com.example.codigotest2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codigotest2.data.local.MovieDatabase
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel  @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getMovieById(movieId: Int): Flow<Movie?> {
        return repository.getMovieById(id = movieId)
    }

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.saveMovie(movie)
            }
        }
    }
}
