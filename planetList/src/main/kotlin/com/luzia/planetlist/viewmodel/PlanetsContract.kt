package com.luzia.planetlist.viewmodel

import com.luzia.architecture.presentation.Effect
import com.luzia.architecture.presentation.Input
import com.luzia.architecture.presentation.State
import com.luzia.architecture.presentation.Result
import com.luzia.planetsharedpresentation.models.PlanetPM

sealed class PlanetListInput : Input
data object LoadPlanetListInput : PlanetListInput()
data class PlanetClickedInput(val planetId: String) : PlanetListInput()

sealed class PlanetListResult : Result
data class LoadPlanetListResult(val planets: List<PlanetPM>) : PlanetListResult()
data class ErrorResult(val message: String) : PlanetListResult()
data class LoadingResult(val isLoading: Boolean) : PlanetListResult()

sealed class PlanetListEffect : Effect
data class GoToPlanetDetailsEffect(val planetId: String) : PlanetListEffect()

sealed class PlanetsState(open val isLoading: Boolean, open val planets: List<PlanetPM>) :
    State

data class InitialListState(override val isLoading: Boolean) : PlanetsState(isLoading, emptyList())

data class ErrorListState(
    val message: String, override val isLoading: Boolean,
) : PlanetsState(isLoading, emptyList())

data class SuccessListState(
    override val planets: List<PlanetPM>, override val isLoading: Boolean,
) : PlanetsState(isLoading, planets)
