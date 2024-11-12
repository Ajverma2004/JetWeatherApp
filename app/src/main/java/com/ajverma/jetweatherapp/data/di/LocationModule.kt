package com.ajverma.jetweatherapp.data.di

import com.ajverma.jetweatherapp.data.location.LocationTrackerImpl
import com.ajverma.jetweatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Singleton
abstract class LocationModule {

    @Singleton
    @Binds
    abstract fun bindLocationTracker(locationTracker: LocationTrackerImpl): LocationTracker
}