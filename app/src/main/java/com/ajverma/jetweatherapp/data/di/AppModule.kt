package com.ajverma.jetweatherapp.data.di

import android.app.Application
import com.ajverma.jetweatherapp.data.Repository.WeatherRepositoryImpl
import com.ajverma.jetweatherapp.data.remote.WeatherApi
import com.ajverma.jetweatherapp.domain.repository.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    private const val BASE_URL = "https://api.open-meteo.com/"

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

}