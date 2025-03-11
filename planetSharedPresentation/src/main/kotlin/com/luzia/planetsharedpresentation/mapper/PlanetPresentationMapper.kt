package com.luzia.planetsharedpresentation.mapper

import com.luzia.domain.model.PlanetDomain
import com.luzia.planetsharedpresentation.models.PlanetPM
import java.text.NumberFormat
import java.util.Locale
import kotlin.text.iterator

object PlanetPresentationMapper {

    fun mapDomainToPresentation(planetDomain: PlanetDomain): PlanetPM = with(planetDomain) {
        val numberFormater = NumberFormat.getInstance()
        PlanetPM(
            climate.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            numberFormater.format(diameter.toLong()),
            gravity.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            if (population.isNumeric()) numberFormater.format(population.toLong()) else population,
            terrain.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
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