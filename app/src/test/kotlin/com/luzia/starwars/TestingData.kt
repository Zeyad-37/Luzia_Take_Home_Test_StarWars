package com.luzia.starwars

import com.luzia.domain.model.PlanetDomain
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM

object TestingData {
    val planetDomain = PlanetDomain(
        name = "Name",
        climate = "Climate",
        diameter = "100",
        gravity = "Gravity",
        terrain = "Terrain",
        population = "100"
    )
    val planetDomain2 = PlanetDomain(
        name = "Name",
        climate = "Climate",
        diameter = "100",
        gravity = "Gravity",
        terrain = "Terrain",
        population = "100"
    )
    val planetPM = PlanetPM(
        name = "Name",
        climate = "Climate",
        diameter = "100",
        gravity = "Gravity",
        terrain = "Terrain",
        population = "100"
    )
    val planetPM2 = PlanetPM(
        name = "Name",
        climate = "Climate",
        diameter = "100",
        gravity = "Gravity",
        terrain = "Terrain",
        population = "100"
    )
}
