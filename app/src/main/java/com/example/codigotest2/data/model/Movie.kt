package com.example.codigotest2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey val id: Long,
    val title: String,
    // Add other relevant movie properties here
)
