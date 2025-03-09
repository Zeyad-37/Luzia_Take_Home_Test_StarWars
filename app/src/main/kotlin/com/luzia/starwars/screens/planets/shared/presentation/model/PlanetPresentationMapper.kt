package com.luzia.starwars.screens.planets.shared.presentation.model

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.luzia.domain.model.PlanetDomain
import java.text.NumberFormat

object PlanetPresentationMapper {

    fun mapDomainToPresentation(planetDomain: PlanetDomain): PlanetPM = with(planetDomain) {
        val numberFormater = NumberFormat.getInstance()
        PlanetPM(
            climate.capitalize(Locale.current),
            numberFormater.format(diameter.toLong()),
            gravity.capitalize(Locale.current),
            name.capitalize(Locale.current),
            if (population.isNumeric()) numberFormater.format(population.toLong()) else population,
            terrain.capitalize(Locale.current)
        )
    }

    private fun String.isNumeric(): Boolean {
        for (c in this) {
            if (!c.isDigit()) return false
        }
        return true
    }

    fun mapDomainToPresentation(planetDomain: List<PlanetDomain>): List<PlanetPM> =
        planetDomain.map { mapDomainToPresentation(it) }

    fun mapPresentationToDomain(planetPM: PlanetPM): PlanetDomain = with(planetPM) {
        PlanetDomain(climate, diameter, gravity, name, population, terrain)
    }

    fun mapPresentationToDomain(planetPM: List<PlanetPM>): List<PlanetDomain> =
        planetPM.map { mapPresentationToDomain(it) }
}
