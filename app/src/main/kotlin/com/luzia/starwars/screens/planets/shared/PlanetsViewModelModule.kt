package com.luzia.starwars.screens.planets.presentation.shared

import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.PlanetDetailState
import com.luzia.domain.usecase.GetPlanetsUseCase
import com.luzia.starwars.screens.planets.presentation.list.viewmodel.InitialListState
import com.luzia.starwars.screens.planets.presentation.list.viewmodel.PlanetsReducer
import com.luzia.starwars.screens.planets.presentation.list.viewmodel.PlanetsState
import com.luzia.data.PlanetRepositoryImpl
import com.luzia.domain.repository.PlanetRepository
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.InitialState
import com.luzia.starwars.screens.planets.presentation.shared.mapper.PlanetPresentationMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object PlanetsViewModelModule {

    @Provides
    @ViewModelScoped
    fun providePlanetsReducer(): PlanetsReducer = PlanetsReducer()

    @Provides
    @ViewModelScoped
    fun provideGetPlanetsUseCase(planetRepository: PlanetRepository): GetPlanetsUseCase =
        GetPlanetsUseCase(planetRepository)

    @Provides
    @ViewModelScoped
    fun providePlanetPresentationMapper(): PlanetPresentationMapper = PlanetPresentationMapper

    @Provides
    @ViewModelScoped
    fun providePlanetListInitialState(): PlanetsState = InitialListState(false)

    @Provides
    @ViewModelScoped
    fun providePlanetDetailInitialState(): PlanetDetailState =
        InitialState(false, "")
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindPlanetRepository(planetRepositoryImpl: PlanetRepositoryImpl): PlanetRepository
}
