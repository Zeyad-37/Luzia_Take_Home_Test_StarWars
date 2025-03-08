package com.luzia.starwars.screens.planets.shared.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Planets")
data class PlanetEntity(
    val climate: String,
    val diameter: String,
    val gravity: String,
    @PrimaryKey val name: String,
    val population: String,
    val terrain: String,
)
