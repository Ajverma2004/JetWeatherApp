package com.ajverma.jetweatherapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavCities")
data class FavouriteCity(
    val city: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
