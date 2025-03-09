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
        diameter = "diameter",
        gravity = "gravity",
        terrain = "terrain",
        population = "population",
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
        diameter = "diameter",
        gravity = "gravity",
        terrain = "terrain",
        population = "population",
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
        diameter = "diameter",
        gravity = "gravity",
        terrain = "terrain",
        population = "population"
    )
    val planetDomain2 = PlanetDomain(
        name = "name",
        climate = "climate",
        diameter = "diameter",
        gravity = "gravity",
        terrain = "terrain",
        population = "population"
    )
    val planetEntity = PlanetEntity(
        name = "name",
        climate = "climate",
        diameter = "diameter",
        gravity = "gravity",
        terrain = "terrain",
        population = "population"
    )
    val planetEntity2 = PlanetEntity(
        name = "name",
        climate = "climate",
        diameter = "diameter",
        gravity = "gravity",
        terrain = "terrain",
        population = "population"
    )
    val planetPM = PlanetPM(
        name = "name",
        climate = "climate",
        diameter = "diameter",
        gravity = "gravity",
        terrain = "terrain",
        population = "population"
    )
    val planetPM2 = PlanetPM(
        name = "name",
        climate = "climate",
        diameter = "diameter",
        gravity = "gravity",
        terrain = "terrain",
        population = "population"
    )
}
