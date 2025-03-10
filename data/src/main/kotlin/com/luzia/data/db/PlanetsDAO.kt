package com.luzia.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetsDAO {

    @Transaction
    @Query("SELECT * FROM Planets ORDER BY name ASC")
    suspend fun getAllPlanets(): List<PlanetEntity>

    @Transaction
    @Query("SELECT * FROM Planets WHERE name = :name")
    suspend fun getPlanetById(name: String): PlanetEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReplace(item: List<PlanetEntity>)
}
