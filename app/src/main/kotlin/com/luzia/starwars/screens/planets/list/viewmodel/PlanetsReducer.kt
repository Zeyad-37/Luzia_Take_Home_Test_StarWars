package com.luzia.starwars.screens.planets.presentation.list.viewmodel

import com.luzia.architecture.presentation.Reducer
import com.luzia.starwars.screens.planets.presentation.shared.models.PlanetPM

class PlanetsReducer : Reducer<PlanetListResult, PlanetsState> {

    override fun reduce(result: PlanetListResult, state: PlanetsState): PlanetsState =
        when (state) {
            is InitialListState -> when (result) {
                is ErrorResult -> errorState(result.message, state.isLoading)
                is LoadingResult -> state.copy(isLoading = result.isLoading)
                is LoadPlanetListResult -> createSuccessState(result.planets, state.isLoading)
            }

            is ErrorListState -> when (result) {
                is ErrorResult -> state.copy(message = result.message, state.isLoading)
                is LoadingResult -> state.copy(isLoading = result.isLoading)
                is LoadPlanetListResult -> createSuccessState(result.planets, state.isLoading)
            }

            is SuccessListState -> when (result) {
                is ErrorResult -> errorState(result.message, state.isLoading)
                is LoadingResult -> state.copy(isLoading = result.isLoading)
                is LoadPlanetListResult -> createSuccessState(result.planets, state.isLoading)
            }
        }

    private fun errorState(message: String, isLoading: Boolean) = ErrorListState(message, isLoading)

    private fun createSuccessState(planets: List<PlanetPM>, isLoading: Boolean) =
        SuccessListState(planets, isLoading)
}
