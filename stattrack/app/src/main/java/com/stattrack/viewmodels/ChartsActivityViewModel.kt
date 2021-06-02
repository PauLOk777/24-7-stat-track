package com.stattrack.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stattrack.models.StatsPeriod
import com.stattrack.models.StatsPrecision
import com.stattrack.repositories.ActivityRepository
import com.stattrack.repositories.TemperatureRepository
import java.time.LocalDate

class ChartsActivityViewModel: ViewModel() {

    private var stepsForChart: MutableLiveData<List<Long>> = MutableLiveData()
    private var temperatureForChart: MutableLiveData<List<Float?>> = MutableLiveData()
    private var currentPrecision: String = StatsPrecision.HOUR.text
    private val activityRepository = ActivityRepository.getInstance()
    private val temperatureRepository = TemperatureRepository.getInstance()

    @SuppressLint("NewApi")
    fun init() {
        stepsForChart.value = activityRepository
            .getStepsForChart(StatsPrecision.HOUR, StatsPeriod.BY_DAY, LocalDate.now())
        temperatureForChart.value = temperatureRepository
            .getTemperatureForChart(StatsPrecision.HOUR, StatsPeriod.BY_DAY, LocalDate.now())
    }

    fun getStepsForChart(): LiveData<List<Long>> = stepsForChart
    fun getTemperatureForChart(): LiveData<List<Float?>> = temperatureForChart
    fun getCurrentPrecision(): String = currentPrecision

    fun setStepsForChart(precision: StatsPrecision, period: StatsPeriod, date: LocalDate) {
        stepsForChart.value = activityRepository.getStepsForChart(precision, period, date)
    }

    fun setTemperatureForChart(precision: StatsPrecision, period: StatsPeriod, date: LocalDate) {
        temperatureForChart.value =
            temperatureRepository.getTemperatureForChart(precision, period, date)
    }

    fun setCurrentPrecision(precision: StatsPrecision) {
        currentPrecision = precision.text
    }
}
