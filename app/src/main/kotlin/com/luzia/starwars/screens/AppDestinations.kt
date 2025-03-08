package com.luzia.starwars.screens

import kotlinx.serialization.Serializable

@Serializable
data object PlanetList

@Serializable
data class PlanetDetail(val planetId: String)
