package com.luzia.starwars.screens.planets.shared

import com.luzia.data.PlanetDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlanetsModule {

    @Provides
    @Singleton
    fun providePlanetDTOMapper(): PlanetDataMapper = PlanetDataMapper
}
