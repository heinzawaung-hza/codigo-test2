package com.example.codigotest2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.codigotest2.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie_table WHERE isPopular = 0")
    fun getUpcomingMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE isPopular = 1")
    fun getPopularMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE id = :movieId")
     fun getMovieById(movieId: Int): Flow<Movie>

    @Update
    fun updateMovieFavouriteStatus(movie: Movie)
}
