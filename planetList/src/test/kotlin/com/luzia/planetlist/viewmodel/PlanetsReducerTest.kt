package com.luzia.planetlist.viewmodel

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlanetsReducerTest {
    private val reducer: PlanetsReducer = PlanetsReducer()
    private val allList = listOf(TestingData.planetPM, TestingData.planetPM2)

    @Test
    fun `reduce LoadingResult with InitialState to InitialState`() {
        val result = LoadingResult(true)
        val expected = InitialListState(true)
        val actual = reducer.reduce(result, InitialListState(false))
        assertEquals(expected, actual)
    }

    @Test
    fun `reduce LoadPlanetsResult with InitialState to SuccessListState`() {
        val result = LoadPlanetListResult(allList)
        val expected = SuccessListState(result.planets, true)
        val actual = reducer.reduce(result, InitialListState(true))
        assertEquals(expected, actual)
    }

    @Test
    fun `reduce ErrorResult with InitialState to ErrorListState`() {
        val result = ErrorResult("Error!")
        val expected = ErrorListState("Error!", true)
        val actual = reducer.reduce(result, InitialListState(true))
        assertEquals(expected, actual)
    }

    @Test
    fun `reduce LoadingResult with ErrorListState to ErrorListState`() {
        val result = LoadingResult(false)
        val expected = ErrorListState("Error!", false)
        val actual = reducer.reduce(result, ErrorListState("Error!", true))
        assertEquals(expected, actual)
    }

    @Test
    fun `reduce LoadPlanetsResult with ErrorListState to SuccessListState`() {
        val result = LoadPlanetListResult(allList)
        val expected = SuccessListState(result.planets, true)
        val actual = reducer.reduce(result, ErrorListState("Error!", true))
        assertEquals(expected, actual)
    }

    @Test
    fun `reduce ErrorResult with ErrorListState to ErrorListState`() {
        val result = ErrorResult("Error!")
        val expected = ErrorListState("Error!", true)
        val actual = reducer.reduce(result, ErrorListState("Error!", true))
        assertEquals(expected, actual)
    }

    @Test
    fun `reduce LoadingResult with SuccessListState to SuccessListState`() {
        val result = LoadingResult(true)
        val expected = SuccessListState(allList, true)
        val actual = reducer.reduce(result, SuccessListState(allList, false))
        assertEquals(expected, actual)
    }

    @Test
    fun `reduce LoadPlanetsResult with SuccessListState to SuccessListState`() {
        val result = LoadPlanetListResult(allList)
        val expected = SuccessListState(result.planets, true)
        val actual = reducer.reduce(result, SuccessListState(allList, true))
        assertEquals(expected, actual)
    }

    @Test
    fun `reduce ErrorResult with SuccessListState to ErrorListState`() {
        val result = ErrorResult("Error!")
        val expected = ErrorListState("Error!", false)
        val actual = reducer.reduce(result, SuccessListState(allList, false))
        assertEquals(expected, actual)
    }
}