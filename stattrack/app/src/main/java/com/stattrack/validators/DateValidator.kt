package com.stattrack.validators

class DateValidator {

    fun validate(date: String) = date.matches(Regex("\\d{1,2}-\\d{1,2}-\\d{4}"))
}