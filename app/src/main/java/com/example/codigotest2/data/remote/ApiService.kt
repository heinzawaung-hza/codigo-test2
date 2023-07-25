package com.example.codigotest2.data.remote

import com.example.codigotest2.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("upcoming")
    suspend fun getUpcomingMovies(@Header("Authorization") authToken: String): Response<MovieResponse>
    @GET("popular")
    suspend fun getPopularMovies(@Header("Authorization") authToken: String) : Response<MovieResponse>
}

