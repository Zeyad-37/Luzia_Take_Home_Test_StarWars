package com.luzia.starwars.screens.planets.shared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlanetEntity::class], version = 1, exportSchema = false)
abstract class PlanetsDatabase : RoomDatabase() {

    abstract fun planetsDao(): PlanetsDAO
}
