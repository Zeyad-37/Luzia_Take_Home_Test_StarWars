package com.luzia.domain.usecase

import com.luzia.domain.model.PlanetDomain
import com.luzia.domain.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(private val planetRepository: PlanetRepository) {

    fun invoke(): Flow<List<PlanetDomain>> = flow { emit(planetRepository.getPlanets()) }
}
