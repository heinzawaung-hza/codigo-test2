package com.example.codigotest2.data.remote

import com.example.codigotest2.data.model.Movie
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Header("Authorization") authToken: String): Response<MovieResponse>
}

data class MovieResponse(
    @SerializedName("results") val movies: List<Movie>
)