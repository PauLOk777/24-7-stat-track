package com.stattrack.network

import com.stattrack.models.ActivityData
import com.stattrack.models.BodySteps
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body

interface ActivityApi {

    @GET("steps/{id}")
    fun getStepsChart(
        @Path("id") id: String,
        @Query("precision") precision: Int,
        @Query("period") period: Int,
        @Query("date") date: Long
    ): Call<List<Long>>

    @GET("steps/summary/{id}")
    fun getActivitySummary(
        @Path("id") id: String,
        @Query("period") period: Int,
        @Query("date") date: Long
    ): Call<ActivityData>

    @PUT("steps/{id}")
    fun putStepsByTimeRange(
        @Path("id") id: String,
        @Body bodySteps: BodySteps
    )
}
