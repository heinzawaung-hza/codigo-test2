package com.example.codigotest2.data.repository

import com.example.codigotest2.data.local.MovieDatabase
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.data.remote.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDatabase: MovieDatabase
) {
    suspend fun fetchAndSaveUpcomingMovies(apiKey: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUpcomingMovies(apiKey)
                if (response.isSuccessful) {
                    val movies = response.body()?.movies ?: emptyList()
                    movieDatabase.movieDao().insertMovies(movies)
                    return@withContext movies
                } else {
                    throw IOException("Network Error")
                }
            } catch (e: IOException) {
                throw e
            }
        }
    }

    fun getUpcomingMoviesOffline(): Flow<List<Movie>> {
        return movieDatabase.movieDao().getUpcomingMovies()
    }
}