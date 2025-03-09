package com.luzia.data.api

import kotlinx.serialization.Serializable

@Serializable
data class PlanetResponse(
    val count: Int,
    val next: String,
    val results: List<PlanetDTO>
)
