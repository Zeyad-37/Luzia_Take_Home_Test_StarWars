package com.luzia.domain.usecase

import com.luzia.domain.model.PlanetDomain

object TestingData {
    val planetDomain = PlanetDomain(
        name = "name",
        climate = "climate",
        diameter = "100",
        gravity = "gravity",
        terrain = "terrain",
        population = "100"
    )
    val planetDomain2 = PlanetDomain(
        name = "name",
        climate = "climate",
        diameter = "100",
        gravity = "gravity",
        terrain = "terrain",
        population = "100"
    )
}
