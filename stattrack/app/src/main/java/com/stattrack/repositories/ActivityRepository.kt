package com.stattrack.repositories

import com.stattrack.models.ActivityData
import com.stattrack.models.StatsPeriod
import com.stattrack.models.StatsPrecision
import java.time.LocalDate

class ActivityRepository private constructor() {

    companion object {
        @Volatile
        private var instance: ActivityRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ActivityRepository().also { instance = it }
            }
    }

    fun getActivityData(period: StatsPeriod, date: LocalDate): ActivityData {
        return getMockDataForActivityData(period)
    }

    fun getStepsForChart(precision: StatsPrecision, period: StatsPeriod, date: LocalDate)
            : List<Long> {
        return getMockDataForStepsChart(period)
    }

    private fun getMockDataForActivityData(period: StatsPeriod): ActivityData {
        return when (period) {
            StatsPeriod.BY_DAY -> ActivityData(4.3f, 4f, 18f)
            StatsPeriod.BY_WEEK -> ActivityData(3.1f, 8f, 30f)
            StatsPeriod.BY_MONTH -> ActivityData(6.3f, 6f, 20f)
            StatsPeriod.BY_YEAR -> ActivityData(1.4f, 1f, 10f)
        }
    }

    private fun getMockDataForStepsChart(period: StatsPeriod): List<Long> {
        return when (period) {
            StatsPeriod.BY_DAY ->
                listOf(
                    0L, 0L, 400L, 200L, 1000L, 0L,
                    102L, 0L, 0L, 432L, 532L, 31L,
                    0L, 0L, 400L, 200L, 1000L, 0L,
                    102L, 0L, 0L, 432L, 532L, 31L
                )
            StatsPeriod.BY_WEEK ->
                listOf(
                    3400L, 4250L, 15000L, 7456L, 8999L, 3000L, 3251L
                )
            StatsPeriod.BY_MONTH ->
                listOf(
                    3400L, 4250L, 15000L, 7456L, 8999L, 3000L, 3251L,
                    3250L, 6320L, 15530L, 6366L, 4723L, 12465L, 6435L,
                    3400L, 4250L, 13620L, 7456L, 8623L, 3000L, 3647L,
                    3400L, 4723L, 4326L, 7456L, 4723L, 3000L, 17685L
                )
            StatsPeriod.BY_YEAR ->
                listOf(
                    20003L, 42156L, 36213L, 12567L, 73221L, 63263L,
                    43235L, 10002L, 63263L, 10573L, 3213L, 36267L
                )
        }
    }
}
