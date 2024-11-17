package com.ajverma.jetweatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouriteCity::class],
    version = 1
)
abstract class RoomDatabase: RoomDatabase() {
    abstract val dao: FavouriteCityDao
}