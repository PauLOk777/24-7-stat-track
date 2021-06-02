package com.stattrack.network

import com.stattrack.models.BodyTemperature
import com.stattrack.models.TemperatureData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body

interface TemperatureApi {

    @GET("temperature/{id}")
    fun getTemperatureChart(
        @Path("id") id: String,
        @Query("precision") precision: Int,
        @Query("period") period: Int,
        @Query("date") date: Long
    ): Call<List<Float?>>

    @GET("temperature/summary/{id}")
    fun getTemperatureSummary(
        @Path("id") id: String,
        @Query("period") period: Int,
        @Query("date") date: Long
    ): Call<TemperatureData>

    @PUT("temperature/{id}")
    fun putTemperatureWithTime(
        @Path("id") id: String,
        @Body bodyTemperature: BodyTemperature
    )
}