package com.luzia.starwars.screens.planets.list.presentation.viewmodel

import app.cash.turbine.test
import com.luzia.starwars.CoroutineTestExtension
import com.luzia.starwars.TestingData
import com.luzia.starwars.screens.planets.list.domain.usecase.GetPlanetsUseCase
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPresentationMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.spy
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(CoroutineTestExtension::class)
class PlanetsViewModelTest {

    private val initialState: InitialListState = InitialListState(false)
    private val reducer: PlanetsReducer = spy()
    private val getPlanetsUseCase: GetPlanetsUseCase = mock()
    private val mapper: PlanetPresentationMapper = spy()
    private val domainList = listOf(TestingData.planetDomain)
    private val pMList = listOf(TestingData.planetPM)
    private val dispatcher = Dispatchers.Default
    private lateinit var planetsViewModel: PlanetsViewModel

    @Test
    fun loadPlanetsInput() = runTest {
        planetsViewModel = PlanetsViewModel(
            getPlanetsUseCase,
            mapper,
            initialState,
            reducer,
            dispatcher
        )
        whenever(getPlanetsUseCase.invoke()).thenReturn(flowOf(domainList))
        planetsViewModel.process(LoadPlanetListInput)
        planetsViewModel.state.test {
            assertEquals(initialState, awaitItem())
            assertEquals(initialState.copy(isLoading = true), awaitItem())
            assertEquals(SuccessListState(pMList, true), awaitItem())
            assertEquals(SuccessListState(pMList, false), awaitItem())
            verify(getPlanetsUseCase).invoke()
        }
    }
}