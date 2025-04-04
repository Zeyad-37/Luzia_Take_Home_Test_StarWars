package com.luzia.domain.usecase

import app.cash.turbine.test
import com.luzia.domain.model.PlanetDomain
import com.luzia.domain.repository.PlanetRepository
import com.luzia.testBase.CoroutineTestExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExtendWith(CoroutineTestExtension::class)
class GetPlanetsUseCaseTest {

    private lateinit var getPlanetsUseCase: GetPlanetsUseCase
    private val planetRepository: PlanetRepository = mock()

    @BeforeEach
    fun setUp() {
        getPlanetsUseCase = GetPlanetsUseCase(planetRepository)
    }

    @Test
    fun `invoke returns planets from repository`() = runTest {
        val expectedPlanets = listOf(TestingData.planetDomain2, TestingData.planetDomain)
        `when`(planetRepository.getPlanets()).thenReturn(expectedPlanets)
        getPlanetsUseCase.invoke().test {
            assertEquals(expectedPlanets, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `invoke returns empty list when repository returns empty list`() = runTest {
        val expectedPlanets = emptyList<PlanetDomain>()
        `when`(planetRepository.getPlanets()).thenReturn(expectedPlanets)
        getPlanetsUseCase.invoke().test {
            assertEquals(expectedPlanets, awaitItem())
            awaitComplete()
        }
    }
}