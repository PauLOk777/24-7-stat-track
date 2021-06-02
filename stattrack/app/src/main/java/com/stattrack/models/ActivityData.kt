package com.stattrack.models

import com.google.gson.annotations.SerializedName

data class ActivityData(
    @SerializedName("distance")
    var distance: Float,
    @SerializedName("maxSpeed")
    var avgSpeed: Float,
    @SerializedName("avgSpeed")
    var maxSpeed: Float
) {
    override fun toString(): String {
        return "$distance - $avgSpeed - $maxSpeed"
    }
}
