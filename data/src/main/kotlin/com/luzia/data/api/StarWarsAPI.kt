package com.luzia.data.api

import retrofit2.http.GET

interface StarWarsAPI {

    @GET("planets")
    suspend fun getPlanets(): PlanetResponse
}
