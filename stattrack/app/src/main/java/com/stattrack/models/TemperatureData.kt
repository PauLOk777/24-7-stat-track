package com.stattrack.models

import com.google.gson.annotations.SerializedName

data class TemperatureData(
    @SerializedName("minTemperature")
    var minimum: Float,
    @SerializedName("maxTemperature")
    var maximum: Float,
    @SerializedName("avgTemperature")
    var avg: Float
) {
    override fun toString(): String {
        return "$minimum - $maximum - $avg"
    }
}
