package com.ajverma.jetweatherapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCityDao {

    @Query("select * from FavCities")
    fun getAllFavouriteCity(): Flow<List<FavouriteCity>>

    @Upsert
    suspend fun upsertFavouriteCity(city: FavouriteCity)

    @Delete
    suspend fun deleteFavouriteCity(city: FavouriteCity)
}