package com.luzia.starwars.di

import android.content.Context
import androidx.room.Room
import com.luzia.starwars.screens.planets.shared.data.db.PlanetsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    @Singleton
    fun provideYourDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, PlanetsDatabase::class.java, "planet_database").build()

    @Provides
    @Singleton
    fun provideYourDao(db: PlanetsDatabase) = db.planetsDao()
}
