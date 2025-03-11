package com.luzia.planetsharedpresentation.mapper

import com.luzia.domain.model.PlanetDomain
import com.luzia.planetsharedpresentation.models.PlanetPM

object TestingData {
    val planetDomain = PlanetDomain(
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
}
