package com.stattrack.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.105:80")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val temperatureApi: TemperatureApi by lazy {
        retrofit.create(TemperatureApi::class.java)
    }

    val activityApi: ActivityApi by lazy {
        retrofit.create(ActivityApi::class.java)
    }
}
