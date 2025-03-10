package com.luzia.data

import com.luzia.data.api.StarWarsAPI
import com.luzia.data.db.PlanetsDAO
import com.luzia.domain.model.PlanetDomain
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.reset
import org.mockito.Mockito.spy
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

class PlanetRepositoryImplTest {

    private lateinit var planetRepositoryImpl: PlanetRepositoryImpl
    private val starWarsAPI: StarWarsAPI = mock()
    private val planetsDAO: PlanetsDAO = mock()
    private val mapper: PlanetDataMapper = spy()

    @BeforeEach
    fun setUp() {
        reset(starWarsAPI, planetsDAO)
        planetRepositoryImpl = PlanetRepositoryImpl(starWarsAPI, planetsDAO, mapper)
    }

    @Test
    fun getPlanets() = runTest {
        val listDTOs = listOf(TestingData.planetDTO, TestingData.planetDTO2)
        val expected = listOf(TestingData.planetDomain, TestingData.planetDomain2)
        whenever(starWarsAPI.getPlanets()).thenReturn(TestingData.planetResponse)
        whenever(planetsDAO.insertAllReplace(mapper.mapDTOsToEntities(listDTOs))).thenReturn(Unit)

        assertEquals(expected, planetRepositoryImpl.getPlanets())

        verify(starWarsAPI).getPlanets()
        verify(planetsDAO).insertAllReplace(listOf(TestingData.planetEntity, TestingData.planetEntity2))
    }

    @Test
    fun `getPlanets - when API throws exception`() = runTest {
        val expected = listOf(TestingData.planetDomain)

        doAnswer { throw IOException("API Error") }.whenever(starWarsAPI).getPlanets()
        whenever(planetsDAO.getAllPlanets()).thenReturn(listOf(TestingData.planetEntity))

        assertEquals(expected, planetRepositoryImpl.getPlanets())

        verify(starWarsAPI).getPlanets()
        verify(planetsDAO).getAllPlanets()
    }

    @Test
    fun `getPlanets - when API throws exception and DB is empty`() = runTest {
        val expected = emptyList<PlanetDomain>()
        doAnswer { throw IOException("API Error") }.whenever(starWarsAPI).getPlanets()
        whenever(planetsDAO.getAllPlanets()).thenReturn(emptyList())

        assertEquals(expected, planetRepositoryImpl.getPlanets())

        verify(starWarsAPI).getPlanets()
        verify(planetsDAO).getAllPlanets()
    }
}