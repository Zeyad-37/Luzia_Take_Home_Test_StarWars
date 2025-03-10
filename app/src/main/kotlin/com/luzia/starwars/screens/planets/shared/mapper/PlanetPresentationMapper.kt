package com.luzia.starwars.screens.planets.presentation.shared.mapper

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.luzia.domain.model.PlanetDomain
import com.luzia.starwars.screens.planets.presentation.shared.models.PlanetPM
import java.text.NumberFormat
import kotlin.text.iterator

object PlanetPresentationMapper {

    fun mapDomainToPresentation(planetDomain: PlanetDomain): PlanetPM = with(planetDomain) {
        val numberFormater = NumberFormat.getInstance()
        PlanetPM(
            climate.capitalize(Locale.Companion.current),
            numberFormater.format(diameter.toLong()),
            gravity.capitalize(Locale.Companion.current),
            name.capitalize(Locale.Companion.current),
            if (population.isNumeric()) numberFormater.format(population.toLong()) else population,
            terrain.capitalize(Locale.Companion.current)
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