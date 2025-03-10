package com.luzia.domain.repository

import com.luzia.architecture.domain.Repository
import com.luzia.domain.model.PlanetDomain

interface PlanetRepository : Repository {
    suspend fun getPlanets(): List<PlanetDomain>

    suspend fun getPlanet(planetId: String): PlanetDomain
}
