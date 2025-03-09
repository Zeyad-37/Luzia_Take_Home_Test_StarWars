package com.luzia.starwars.screens.planets.detail.presentation.viewmodel

import app.cash.turbine.test
import com.luzia.domain.repository.PlanetRepository
import com.luzia.starwars.TestingData
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPresentationMapper
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.reset
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PlanetDetailViewModelTest {
    private val initialState: InitialState = InitialState(false, "2")
    private val planetRepository: PlanetRepository = mock()
    private val planetMapper: PlanetPresentationMapper = spy()
    private lateinit var planetDetailViewModel: PlanetDetailViewModel

    @BeforeEach
    fun setUp() {
        reset(planetRepository, planetMapper)
        planetDetailViewModel = PlanetDetailViewModel(planetRepository, planetMapper, initialState)
    }

    @Test
    fun onLoadPlanet() = runTest {
        whenever(planetRepository.getPlanet("2")).thenReturn(TestingData.planetDomain2)
        planetDetailViewModel.process(LoadPlanetInput("2"))
        planetDetailViewModel.state.test {
            assertEquals(initialState, awaitItem())
            assertEquals(initialState.copy(isLoading = true), awaitItem())
            assertEquals(
                SuccessState(TestingData.planetPM2, false),
                awaitItem()
            )
            verify(planetRepository).getPlanet("2")
            verify(planetMapper).mapDomainToPresentation(TestingData.planetDomain2)
        }
    }

    @Test
    fun onBackButtonTappedInput() = runTest {
        planetDetailViewModel.process(BackButtonTappedInput)
        planetDetailViewModel.effect.test {
            assertEquals(GoBackEffect, awaitItem())
        }
        planetDetailViewModel.state.test {
            assertEquals(initialState, awaitItem())
            verify(planetRepository, never()).getPlanet(anyString())
            verify(planetMapper, never()).mapDomainToPresentation(TestingData.planetDomain2)
        }
    }
}