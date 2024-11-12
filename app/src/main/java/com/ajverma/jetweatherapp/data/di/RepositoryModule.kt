package com.ajverma.jetweatherapp.data.di

import com.ajverma.jetweatherapp.data.Repository.WeatherRepositoryImpl
import com.ajverma.jetweatherapp.data.location.LocationTrackerImpl
import com.ajverma.jetweatherapp.domain.location.LocationTracker
import com.ajverma.jetweatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Singleton
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}