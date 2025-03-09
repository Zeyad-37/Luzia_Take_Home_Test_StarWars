package com.luzia.domain.repository

import com.luzia.architecture.domain.Repository
import com.luzia.domain.model.PlanetDomain
import kotlinx.coroutines.flow.Flow

interface PlanetRepository : Repository {
    suspend fun getPlanets(): List<PlanetDomain>

    suspend fun syncPlanets(): Boolean

    fun getPlanetsOfflineFirst(): Flow<List<PlanetDomain>>

    suspend fun getPlanet(planetId: String): PlanetDomain
}
