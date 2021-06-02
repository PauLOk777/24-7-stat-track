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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class MainActivityViewModel: ViewModel() {

    private val activityData: MutableLiveData<ActivityData> = MutableLiveData()
    private var temperatureData: MutableLiveData<TemperatureData> = MutableLiveData()
    private val activityRepository = ActivityRepository.getInstance()
    private val temperatureRepository = TemperatureRepository.getInstance()
    private lateinit var userId: String

    @SuppressLint("NewApi")
    fun init(userId: String) {
        this.userId = userId

        activityRepository
            .getActivityData(StatsPeriod.BY_DAY, System.currentTimeMillis(), userId).enqueue(object: Callback<ActivityData> {
                override fun onResponse(call: Call<ActivityData>, response: Response<ActivityData>) {
                    activityData.value = response.body()
                }

                override fun onFailure(call: Call<ActivityData>, t: Throwable) {
                    println("getActivityData MainActivityViewModel error")
                }
            })

        temperatureRepository
            .getTemperatureData(StatsPeriod.BY_DAY, System.currentTimeMillis(), userId).enqueue(object: Callback<TemperatureData> {
                override fun onResponse(call: Call<TemperatureData>, response: Response<TemperatureData>) {
                    temperatureData.value = response.body()
                }

                override fun onFailure(call: Call<TemperatureData>, t: Throwable) {
                    println("getActivityData MainActivityViewModel error")
                }
            })
//        activityData.value = activityRepository
//            .getActivityData(StatsPeriod.BY_DAY, System.currentTimeMillis(), userId)
//        temperatureData.value = temperatureRepository
//            .getTemperatureData(StatsPeriod.BY_DAY, System.currentTimeMillis(), userId)
    }

    fun getActivityData(): LiveData<ActivityData> = activityData
    fun getTemperatureData(): LiveData<TemperatureData> = temperatureData

}
