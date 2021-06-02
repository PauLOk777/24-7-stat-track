package com.stattrack.models

data class ActivityData(var distance: Float, var avgSpeed: Float, var maxSpeed: Float) {
    override fun toString(): String {
        return "$distance - $avgSpeed - $maxSpeed"
    }
}
