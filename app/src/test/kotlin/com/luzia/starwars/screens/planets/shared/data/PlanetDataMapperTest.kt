package com.luzia.starwars.screens.planets.shared.data

import com.luzia.starwars.TestingData
import com.luzia.starwars.screens.planets.list.data.api.PlanetDTO
import com.luzia.starwars.screens.planets.list.domain.model.PlanetDomain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlanetDataMapperTest {
    private val mapper: PlanetDataMapper = PlanetDataMapper

    @Test
    fun mapDTOsToDomains_validDTOs_returnsCorrectDomains() {
        val dtos = listOf(TestingData.planetDTO)
        val expectedDomains = listOf(TestingData.planetDomain)
        val actualDomains = mapper.mapDTOsToDomains(dtos)
        assertEquals(expectedDomains, actualDomains)
    }

    @Test
    fun mapDTOsToDomains_emptyDTOs_returnsEmptyDomains() {
        val dtos = emptyList<PlanetDTO>()
        val expectedDomains = emptyList<PlanetDomain>()
        val actualDomains = mapper.mapDTOsToDomains(dtos)
        assertEquals(expectedDomains, actualDomains)
    }

    @Test
    fun mapEntityToDomain_validEntity_returnsCorrectDomain() {
        val planetWithDependencies = TestingData.planetEntity
        val expectedDomain = TestingData.planetDomain
        val actualDomain = mapper.mapEntityToDomain(planetWithDependencies)
        assertEquals(expectedDomain, actualDomain)
    }

    @Test
    fun mapDTOsToEntities_validDTOs_returnsCorrectEntities() {
        val dtos = listOf(TestingData.planetDTO, TestingData.planetDTO2)
        val expectedEntity = listOf(TestingData.planetEntity, TestingData.planetEntity2)
        val actualEntity = mapper.mapDTOsToEntities(dtos)
        assertEquals(expectedEntity, actualEntity)
    }
}