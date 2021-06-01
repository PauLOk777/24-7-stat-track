package com.stattrack.repositories

import com.stattrack.models.StatsPeriod
import com.stattrack.models.StatsPrecision
import com.stattrack.models.TemperatureData
import java.time.LocalDate

class TemperatureRepository private constructor() {

    companion object {
        @Volatile
        private var instance: TemperatureRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: TemperatureRepository().also { instance = it }
            }
    }

    fun getTemperatureData(period: StatsPeriod, date: LocalDate): TemperatureData {
        return getMockDataForActivityData(period)
    }

    fun getTemperatureForChart(precision: StatsPrecision, period: StatsPeriod, date: LocalDate)
            : List<Float?> {
        return getMockDataForTemperatureChart(period)
    }

    private fun getMockDataForActivityData(period: StatsPeriod): TemperatureData {
        return when (period) {
            StatsPeriod.BY_DAY -> TemperatureData(-1f, 18f, 4f)
            StatsPeriod.BY_WEEK -> TemperatureData(-6f, 10f, 0f)
            StatsPeriod.BY_MONTH -> TemperatureData(6f, 10f, 20f)
            StatsPeriod.BY_YEAR -> TemperatureData(-10f, 5f, 10f)
        }
    }

    private fun getMockDataForTemperatureChart(period: StatsPeriod): List<Float?> {
        return when (period) {
            StatsPeriod.BY_DAY ->
                listOf(
                    0f, null, null, 2f, 1f, 0f,
                    2f, 0f, 0f, null, 2f, 1f,
                    0f, 0f, 4f, 2f, null, null,
                    null, 0f, 0f, 4f, 5f, 3f
                )
            StatsPeriod.BY_WEEK -> listOf(2f, 0f, 0f, 3f, 5f, 3f, null)
            StatsPeriod.BY_MONTH ->
                listOf(
                    0f, 0f, 0f, 0f, 0f, 0f,
                    0f, 0f, 4f, 2f, 1f, 0f,
                    1f, null, 0f, 4f, 2f, null,
                    4f, 1f, 5f, null, 1f, 0f,
                    4f, 1f, null, null, 1f, 3.4f
                )
            StatsPeriod.BY_YEAR ->
                listOf(
                    0f, 0f, 4f, 2f, 10f, null,
                    2f, 1f, null, 3f, 1f, 0f
                )
        }
    }
}
