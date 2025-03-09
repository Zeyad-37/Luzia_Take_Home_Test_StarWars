package com.luzia.starwars

import com.luzia.starwars.screens.planets.list.data.api.PlanetDTO
import com.luzia.starwars.screens.planets.list.data.api.PlanetResponse
import com.luzia.starwars.screens.planets.list.domain.model.PlanetDomain
import com.luzia.starwars.screens.planets.shared.data.db.PlanetEntity
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM

object TestingData {
    val planetDTO = PlanetDTO(
        name = "name",
        climate = "climate",
        diameter = "100",
        gravity = "1 gravity",
        terrain = "terrain",
        population = "100",
        url = "url",
        films = listOf(),
        edited = "edited",
        rotationPeriod = "rPeriod",
        residents = listOf(),
        created = "created",
        orbitalPeriod = "oPeriod",
        surfaceWater = "sWater",
    )
    val planetDTO2 = PlanetDTO(
        name = "name",
        climate = "climate",
        diameter = "100",
        gravity = "gravity",
        terrain = "terrain",
        population = "100",
        url = "url",
        films = listOf(),
        edited = "edited",
        rotationPeriod = "rPeriod",
        residents = listOf(),
        created = "created",
        orbitalPeriod = "oPeriod",
        surfaceWater = "sWater",
    )
    val planetResponse = PlanetResponse(1, "next", listOf(planetDTO, planetDTO2))
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
    val planetEntity = PlanetEntity(
        name = "name",
        climate = "climate",
        diameter = "100",
        gravity = "gravity",
        terrain = "terrain",
        population = "100"
    )
    val planetEntity2 = PlanetEntity(
        name = "name",
        climate = "climate",
        diameter = "100",
        gravity = "gravity",
        terrain = "terrain",
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
