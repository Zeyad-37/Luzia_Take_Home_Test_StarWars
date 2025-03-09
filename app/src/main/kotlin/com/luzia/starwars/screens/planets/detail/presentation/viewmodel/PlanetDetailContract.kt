package com.luzia.starwars.screens.planets.detail.presentation.viewmodel

import com.luzia.architecture.presentation.Effect
import com.luzia.architecture.presentation.Input
import com.luzia.architecture.presentation.State
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM

sealed class PlanetDetailInput : Input
data class LoadPlanetInput(val planetId: String) : PlanetDetailInput()
data object BackButtonTappedInput : PlanetDetailInput()

sealed class PlanetDetailEffect : Effect
data object GoBackEffect : PlanetDetailEffect()

sealed class PlanetDetailState(open val isLoading: Boolean) : State

data class InitialState(override val isLoading: Boolean, val planetId: String) : PlanetDetailState(isLoading)

data class ErrorState(val message: String, override val isLoading: Boolean) : PlanetDetailState(isLoading)

data class SuccessState(val planet: PlanetPM, override val isLoading: Boolean) : PlanetDetailState(isLoading)
