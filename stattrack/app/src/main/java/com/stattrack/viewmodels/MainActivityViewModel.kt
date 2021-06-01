package com.stattrack.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stattrack.models.ActivityData
import com.stattrack.models.StatsPeriod
import com.stattrack.models.TemperatureData
import com.stattrack.repositories.ActivityRepository
import com.stattrack.repositories.TemperatureRepository
import java.time.LocalDate

class MainActivityViewModel: ViewModel() {

    private val activityData: MutableLiveData<ActivityData> = MutableLiveData()
    private var temperatureData: MutableLiveData<TemperatureData> = MutableLiveData()
    private val activityRepository = ActivityRepository.getInstance()
    private val temperatureRepository = TemperatureRepository.getInstance()

    @SuppressLint("NewApi")
    fun init() {
        activityData.value = activityRepository
            .getActivityData(StatsPeriod.BY_DAY, LocalDate.now())
        temperatureData.value = temperatureRepository
            .getTemperatureData(StatsPeriod.BY_DAY, LocalDate.now())
    }

    fun getActivityData(): LiveData<ActivityData> = activityData
    fun getTemperatureData(): LiveData<TemperatureData> = temperatureData

}
