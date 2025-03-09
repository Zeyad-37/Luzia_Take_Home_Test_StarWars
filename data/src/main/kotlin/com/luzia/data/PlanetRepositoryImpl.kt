package com.luzia.data

import android.util.Log
import com.luzia.architecture.Dispatcher
import com.luzia.architecture.MyDispatchers
import com.luzia.data.api.PlanetDTO
import com.luzia.data.api.StarWarsAPI
import com.luzia.data.db.PlanetsDAO
import com.luzia.domain.model.PlanetDomain
import com.luzia.domain.repository.PlanetRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
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
            val planetsDTOs = syncPlanetsHelper()
            planetDataMapper.mapDTOsToDomains(planetsDTOs)
        } catch (e: IOException) {
            planetsDAO.getAllPlanets().map { planetDataMapper.mapEntityToDomain(it) }
        }

    override suspend fun syncPlanets(): Boolean =
        try {
            syncPlanetsHelper().let { true }
        } catch (e: IOException) {
            Log.e("PlanetRepository", e.message.orEmpty())
            false
        }

    private suspend fun syncPlanetsHelper(): List<PlanetDTO> =
        starWarsAPI.getPlanets().run {
            planetsDAO.insertAllIgnore(planetDataMapper.mapDTOsToEntities(results))
            results
        }

    override fun getPlanetsOfflineFirst(): Flow<List<PlanetDomain>> =
        planetsDAO.getAllPlanetsOfflineFirst()
            .map { planetDataMapper.mapEntitiesToDomains(it) }
            .flowOn(ioDispatcher)

    override suspend fun getPlanet(planetId: String): PlanetDomain =
        planetDataMapper.mapEntityToDomain(planetsDAO.getPlanetById(planetId))
}
