package com.luzia.starwars.screens.planets.shared.data

import com.luzia.starwars.screens.planets.list.data.api.PlanetDTO
import com.luzia.starwars.screens.planets.list.domain.model.PlanetDomain
import com.luzia.starwars.screens.planets.shared.data.db.PlanetEntity
import javax.inject.Inject

class PlanetDataMapper @Inject constructor() {

    fun mapDTOsToDomains(planetDTOs: List<PlanetDTO>): List<PlanetDomain> = planetDTOs.map {
        with(it) { PlanetDomain(climate, diameter, gravity, name, population, terrain) }
    }

    fun mapEntityToDomain(planetEntity: PlanetEntity): PlanetDomain =
        with(planetEntity) { PlanetDomain(climate, diameter, gravity, name, population, terrain) }

    fun mapEntitiesToDomains(planetEntity: List<PlanetEntity>): List<PlanetDomain> =
        planetEntity.map { mapEntityToDomain(it) }

    fun mapDTOsToEntities(planetDTOs: List<PlanetDTO>): List<PlanetEntity> = planetDTOs.map {
        with(it) { PlanetEntity(climate, diameter, gravity, name, population, terrain) }
    }

    fun mapDomainToEntity(planet: PlanetDomain): PlanetEntity = with(planet) {
        PlanetEntity(climate, diameter, gravity, name, population, terrain)
    }
}
