package com.luzia.planetdetail

import com.luzia.planetdetail.viewmodel.InitialState
import com.luzia.planetdetail.viewmodel.PlanetDetailState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PlanetsViewModelModule {

    @Provides
    @ViewModelScoped
    fun providePlanetDetailInitialState(): PlanetDetailState = InitialState(false, "")
}
