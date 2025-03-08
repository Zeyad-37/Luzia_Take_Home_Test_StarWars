package com.luzia.starwars.screens.planets.list.data.api

import retrofit2.http.GET

interface StarWarsAPI {

    @GET("planets")
    suspend fun getPlanets(): PlanetResponse
}
