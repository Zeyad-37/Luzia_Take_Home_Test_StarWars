package com.luzia.starwars.screens.planets.shared.presentation.model

import com.luzia.starwars.TestingData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlanetPresentationMapperTest {
    private val mapper: PlanetPresentationMapper = PlanetPresentationMapper

    @Test
    fun mapDomainListToPresentation() {
        val domains = listOf(TestingData.planetDomain)
        val expectedPresentations = listOf(TestingData.planetPM)
        val actualDomains = mapper.mapDomainToPresentation(domains)
        assertEquals(expectedPresentations, actualDomains)
    }

    @Test
    fun mapPresentationListToDomain() {
        val presentations = listOf(TestingData.planetPM)
        val expectedDomains = listOf(TestingData.planetDomain)
        val actualDomains = mapper.mapPresentationToDomain(presentations)
        assertEquals(expectedDomains, actualDomains)
    }
}
