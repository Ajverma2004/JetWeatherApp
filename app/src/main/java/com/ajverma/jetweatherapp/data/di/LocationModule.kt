package com.ajverma.jetweatherapp.data.di

import com.ajverma.jetweatherapp.data.location.LocationTrackerImpl
import com.ajverma.jetweatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(locationTracker: LocationTrackerImpl): LocationTracker
}