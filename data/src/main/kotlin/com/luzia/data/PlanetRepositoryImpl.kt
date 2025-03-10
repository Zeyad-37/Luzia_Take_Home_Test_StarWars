package com.luzia.data

import android.util.Log
import com.luzia.architecture.Dispatcher
import com.luzia.architecture.MyDispatchers
import com.luzia.data.api.StarWarsAPI
import com.luzia.data.db.PlanetsDAO
import com.luzia.domain.model.PlanetDomain
import com.luzia.domain.repository.PlanetRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okio.IOException
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val starWarsAPI: StarWarsAPI,
    private val planetsDAO: PlanetsDAO,
    private val planetDataMapper: PlanetDataMapper,
    @Dispatcher(MyDispatchers.IO) override val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PlanetRepository {

    override suspend fun getPlanets(): List<PlanetDomain> =
        try {
            val response = starWarsAPI.getPlanets()
            planetsDAO.insertAllReplace(planetDataMapper.mapDTOsToEntities(response.results))
            planetDataMapper.mapDTOsToDomains(response.results)
        } catch (e: IOException) {
            Log.e("PlanetRepository", e.message.orEmpty())
            planetsDAO.getAllPlanets().map { planetDataMapper.mapEntityToDomain(it) }
        }

    override suspend fun getPlanet(planetId: String): PlanetDomain =
        planetDataMapper.mapEntityToDomain(planetsDAO.getPlanetById(planetId))
}
