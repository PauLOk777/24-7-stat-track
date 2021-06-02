package com.stattrack.models

enum class StatsPrecision(val value: Int, val text: String) {
    HOUR(1, "hours"),
    DAY(2, "days"),
    MONTH(3, "months")
}