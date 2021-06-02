package com.stattrack.models

data class TemperatureData(var minimum: Float, var maximum: Float, var avg: Float) {
    override fun toString(): String {
        return "$minimum - $maximum - $avg"
    }
}
