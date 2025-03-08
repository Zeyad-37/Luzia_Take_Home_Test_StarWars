package com.luzia.starwars.screens.planets.list.presentation.viewmodel

import com.luzia.starwars.architecture.presentation.Effect
import com.luzia.starwars.architecture.presentation.Input
import com.luzia.starwars.architecture.presentation.Result
import com.luzia.starwars.architecture.presentation.State
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM

sealed class PlanetListInput : Input
data object LoadPlanetListInput : PlanetListInput()
data class PlanetClickedInput(val planetId: String) : PlanetListInput()

sealed class PlanetListResult : Result
data class LoadPlanetListResult(val planets: List<PlanetPM>) : PlanetListResult()
data class ErrorResult(val message: String) : PlanetListResult()
data class LoadingResult(val isLoading: Boolean) : PlanetListResult()

sealed class PlanetListEffect : Effect
data class GoToPlanetDetailsEffect(val planetId: String) : PlanetListEffect()

sealed class PlanetsState(open val isLoading: Boolean, open val planets: List<PlanetPM>) : State

data class InitialListState(override val isLoading: Boolean) : PlanetsState(isLoading, emptyList())

data class ErrorListState(
    val message: String, override val isLoading: Boolean,
) : PlanetsState(isLoading, emptyList())

data class SuccessListState(
    override val planets: List<PlanetPM>, override val isLoading: Boolean,
) : PlanetsState(isLoading, planets)
