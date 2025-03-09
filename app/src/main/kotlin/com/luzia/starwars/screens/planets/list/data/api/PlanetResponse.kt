package com.luzia.starwars.screens.planets.list.data.api

import kotlinx.serialization.Serializable

@Serializable
data class PlanetResponse(
    val count: Int,
    val next: String,
    val results: List<PlanetDTO>
)
