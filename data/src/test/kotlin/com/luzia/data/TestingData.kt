package com.luzia.data

import com.luzia.data.api.PlanetDTO
import com.luzia.data.api.PlanetResponse
import com.luzia.data.db.PlanetEntity
import com.luzia.domain.model.PlanetDomain

object TestingData {
    val planetDTO = PlanetDTO(
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
}
