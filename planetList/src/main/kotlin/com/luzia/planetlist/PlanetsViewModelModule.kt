package com.luzia.planetlist

import com.luzia.data.PlanetRepositoryImpl
import com.luzia.domain.repository.PlanetRepository
import com.luzia.domain.usecase.GetPlanetsUseCase
import com.luzia.planetlist.viewmodel.InitialListState
import com.luzia.planetlist.viewmodel.PlanetsReducer
import com.luzia.planetlist.viewmodel.PlanetsState
import com.luzia.planetsharedpresentation.mapper.PlanetPresentationMapper
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
    fun providePlanetPresentationMapper(): PlanetPresentationMapper = PlanetPresentationMapper

    @Provides
    @ViewModelScoped
    fun providePlanetsReducer(): PlanetsReducer = PlanetsReducer()

    @Provides
    @ViewModelScoped
    fun provideGetPlanetsUseCase(planetRepository: PlanetRepository): GetPlanetsUseCase =
        GetPlanetsUseCase(planetRepository)

    @Provides
    @ViewModelScoped
    fun providePlanetListInitialState(): PlanetsState = InitialListState(false)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindPlanetRepository(planetRepositoryImpl: PlanetRepositoryImpl): PlanetRepository
}
