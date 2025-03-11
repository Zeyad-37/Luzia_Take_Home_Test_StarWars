package com.luzia.data.di

import android.util.Log
import com.luzia.data.PlanetDataMapper
import com.luzia.data.api.StarWarsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    private const val TIMEOUT_VALUE = 10L

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://swapi.dev/api/"

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(httpLogger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor =
        HttpLoggingInterceptor(httpLogger).also { it.level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideHTTPLogger(): HttpLoggingInterceptor.Logger =
        HttpLoggingInterceptor.Logger { message -> Log.d("Networking", message) }


    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        encodeDefaults = true
    }


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun provideTransactionsAPI(retrofit: Retrofit): StarWarsAPI = retrofit.create(StarWarsAPI::class.java)

    @Provides
    @Singleton
    fun providePlanetDataMapper(): PlanetDataMapper = PlanetDataMapper
}