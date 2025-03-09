package com.luzia.data

import com.luzia.data.api.PlanetDTO
import com.luzia.data.db.PlanetEntity
import com.luzia.domain.model.PlanetDomain

object PlanetDataMapper {

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
