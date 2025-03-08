package com.luzia.starwars.screens.planets.shared.presentation.model

import com.luzia.starwars.screens.planets.list.domain.model.PlanetDomain

object PlanetPresentationMapper {

    fun mapDomainToPresentation(planetDomain: PlanetDomain): PlanetPM = with(planetDomain) {
        PlanetPM(climate, diameter, gravity, name, population, terrain)
    }

    fun mapDomainToPresentation(planetDomain: List<PlanetDomain>): List<PlanetPM> =
        planetDomain.map { mapDomainToPresentation(it) }

    fun mapPresentationToDomain(planetPM: PlanetPM): PlanetDomain = with(planetPM) {
        PlanetDomain(climate, diameter, gravity, name, population, terrain)
    }

    fun mapPresentationToDomain(planetPM: List<PlanetPM>): List<PlanetDomain> =
        planetPM.map { mapPresentationToDomain(it) }
}
