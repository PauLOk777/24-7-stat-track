package com.stattrack.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stattrack.models.StatsPeriod
import com.stattrack.models.StatsPrecision
import com.stattrack.repositories.ActivityRepository
import com.stattrack.repositories.TemperatureRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChartsActivityViewModel: ViewModel() {

    private var stepsForChart: MutableLiveData<List<Long>> = MutableLiveData()
    private var temperatureForChart: MutableLiveData<List<Float?>> = MutableLiveData()
    private var currentPrecision: String = StatsPrecision.HOUR.text
    private val activityRepository = ActivityRepository.getInstance()
    private val temperatureRepository = TemperatureRepository.getInstance()
    private lateinit var userId: String

    @SuppressLint("NewApi")
    fun init(userId: String) {
        this.userId = userId
        setStepsForChart(StatsPrecision.HOUR, StatsPeriod.BY_DAY, System.currentTimeMillis())
        setTemperatureForChart(StatsPrecision.HOUR, StatsPeriod.BY_DAY, System.currentTimeMillis())
//        stepsForChart.value = activityRepository
//            .getStepsForChart(StatsPrecision.HOUR, StatsPeriod.BY_DAY, System.currentTimeMillis(), userId)
//        temperatureForChart.value = temperatureRepository
//            .getTemperatureForChart(StatsPrecision.HOUR, StatsPeriod.BY_DAY, System.currentTimeMillis(), userId)
    }

    fun getStepsForChart(): LiveData<List<Long>> = stepsForChart
    fun getTemperatureForChart(): LiveData<List<Float?>> = temperatureForChart
    fun getCurrentPrecision(): String = currentPrecision

    fun setStepsForChart(precision: StatsPrecision, period: StatsPeriod, date: Long) {
        activityRepository
            .getStepsForChart(precision, period, date, userId).enqueue(object: Callback<List<Long>> {
                override fun onResponse(call: Call<List<Long>>, response: Response<List<Long>>) {
                    stepsForChart.value = response.body()
                }

                override fun onFailure(call: Call<List<Long>>, t: Throwable) {
                    println("getStepsForChart ChartsActivityViewModel error")
                }
            })
    }

    fun setTemperatureForChart(precision: StatsPrecision, period: StatsPeriod, date: Long) {
        temperatureRepository
            .getTemperatureForChart(precision, period, date, userId).enqueue(object: Callback<List<Float?>> {
                override fun onResponse(call: Call<List<Float?>>, response: Response<List<Float?>>) {
                    temperatureForChart.value = response.body()
                }

                override fun onFailure(call: Call<List<Float?>>, t: Throwable) {
                    println("getTemperatureForChart ChartsActivityViewModel error")
                }
            })
    }

//    fun setStepsForChart(precision: StatsPrecision, period: StatsPeriod, date: Long) {
//        stepsForChart.value = activityRepository.getStepsForChart(precision, period, date, userId)
//    }
//
//    fun setTemperatureForChart(precision: StatsPrecision, period: StatsPeriod, date: Long) {
//        temperatureForChart.value =
//            temperatureRepository.getTemperatureForChart(precision, period, date, userId)
//    }


    fun setCurrentPrecision(precision: StatsPrecision) {
        currentPrecision = precision.text
    }
}
