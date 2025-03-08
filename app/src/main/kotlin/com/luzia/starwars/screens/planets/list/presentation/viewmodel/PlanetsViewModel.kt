package com.luzia.starwars.screens.planets.list.presentation.viewmodel

import com.luzia.starwars.architecture.presentation.Result
import com.luzia.starwars.architecture.presentation.ViewModel
import com.luzia.starwars.di.Dispatcher
import com.luzia.starwars.di.MyDispatchers
import com.luzia.starwars.screens.planets.list.domain.usecase.GetPlanetsUseCase
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase,
    private val planetUiMapper: PlanetPresentationMapper,
    initialState: PlanetsState,
    reducer: PlanetsReducer,
    @Dispatcher(MyDispatchers.Default) coroutineDispatcher: CoroutineDispatcher,
) : ViewModel<PlanetListInput, PlanetListResult, PlanetsState, PlanetListEffect>(
    initialState, reducer, coroutineDispatcher
) {

    override fun resolve(input: PlanetListInput, state: PlanetsState): Flow<Result> =
        when (input) {
            LoadPlanetListInput -> onLoadPlanets()
            is PlanetClickedInput -> flowOf(GoToPlanetDetailsEffect(input.planetId))
        }

    private fun onLoadPlanets(): Flow<Result> = getPlanetsUseCase.invoke()
        .flatMapConcat { planets ->
            flowOf(
                LoadPlanetListResult(planetUiMapper.mapDomainToPresentation(planets)),
                LoadingResult(false)
            )
        }.onEmpty { emit(LoadingResult(false)) }
        .onStart { emit(LoadingResult(true)) }
}
