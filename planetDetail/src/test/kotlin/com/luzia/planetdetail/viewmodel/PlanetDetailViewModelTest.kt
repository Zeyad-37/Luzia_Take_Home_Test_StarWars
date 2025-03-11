package com.luzia.planetdetail.viewmodel

import app.cash.turbine.test
import com.luzia.domain.repository.PlanetRepository
import com.luzia.planetsharedpresentation.mapper.PlanetPresentationMapper
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
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
            Assertions.assertEquals(initialState, awaitItem())
            Assertions.assertEquals(initialState.copy(isLoading = true), awaitItem())
            Assertions.assertEquals(
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
            Assertions.assertEquals(GoBackEffect, awaitItem())
        }
        planetDetailViewModel.state.test {
            Assertions.assertEquals(initialState, awaitItem())
            verify(planetRepository, never()).getPlanet(ArgumentMatchers.anyString())
            verify(planetMapper, never()).mapDomainToPresentation(TestingData.planetDomain2)
        }
    }
}