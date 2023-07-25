package com.example.codigotest2.data.repository

import com.example.codigotest2.data.local.MovieDatabase
import com.example.codigotest2.data.model.Movie
import com.example.codigotest2.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject


class Repository @Inject constructor(
    private val apiService: ApiService,
    private val movieDatabase: MovieDatabase
) {
    suspend fun fetchAndSaveUpcomingMovies(apiKey: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUpcomingMovies(apiKey)
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    val mList = movies.map { m ->
                        m.copy(isPopular = 0)
                    }
                    movieDatabase.movieDao().insertMovies(mList)
                    return@withContext mList
                } else {
                    throw IOException("Network Error")
                }
            } catch (e: IOException) {
                throw e
            }
        }
    }

    suspend fun fetchAndSavePopularMovies(apiKey: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getPopularMovies(apiKey)
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    val mList = movies.map { m ->
                        m.copy(isPopular = 1)
                    }
                    movieDatabase.movieDao().insertMovies(mList)
                    return@withContext mList
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

    fun getMovieById(id:Int): Flow<Movie> {
        return movieDatabase.movieDao().getMovieById(movieId = id)
    }

    fun saveMovie(movie: Movie) {
        movieDatabase.movieDao().updateMovieFavouriteStatus(movie)
    }


    fun getPopularMoviesOffline(): Flow<List<Movie>> {
        return movieDatabase.movieDao().getPopularMovies()
    }
}