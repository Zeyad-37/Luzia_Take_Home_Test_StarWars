package com.luzia.starwars.screens.planets.shared.domain.repository

import com.luzia.starwars.architecture.domain.Repository
import com.luzia.starwars.screens.planets.list.domain.model.PlanetDomain
import kotlinx.coroutines.flow.Flow

interface PlanetRepository : Repository {
    suspend fun getPlanets(): List<PlanetDomain>

    suspend fun syncPlanets(): Boolean

    fun getPlanetsOfflineFirst(): Flow<List<PlanetDomain>>

    suspend fun getPlanet(planetId: String): PlanetDomain
}
