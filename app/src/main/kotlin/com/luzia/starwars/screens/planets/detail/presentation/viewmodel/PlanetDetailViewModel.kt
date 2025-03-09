package com.luzia.starwars.screens.planets.detail.presentation.viewmodel

import com.luzia.architecture.Dispatcher
import com.luzia.architecture.MyDispatchers
import com.luzia.architecture.presentation.Result
import com.luzia.architecture.presentation.ViewModel
import com.luzia.domain.repository.PlanetRepository
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPresentationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val planetRepository: PlanetRepository,
    private val planetUiMapper: PlanetPresentationMapper,
    initialState: PlanetDetailState = InitialState(false, ""),
    @Dispatcher(MyDispatchers.Default) dispatcher: CoroutineDispatcher =  Dispatchers.Default,
) : ViewModel<PlanetDetailInput, Result, PlanetDetailState, PlanetDetailEffect>(
    initialState, dispatcher = dispatcher
) {

    override fun resolve(input: PlanetDetailInput, state: PlanetDetailState): Flow<Result> =
        when (input) {
            is LoadPlanetInput -> onLoadPlanet(input.planetId)
            BackButtonTappedInput -> flowOf(GoBackEffect)
        }


    private fun onLoadPlanet(planetId: String): Flow<Result> = flow {
        emit(InitialState(true, planetId))
        emit(
            SuccessState(
                planetUiMapper.mapDomainToPresentation(planetRepository.getPlanet(planetId)), false
            )
        )
    }
}
