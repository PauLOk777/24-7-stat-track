package com.stattrack

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.stattrack.models.Measures
import com.stattrack.models.StatsPeriod
import com.stattrack.validators.DateValidator
import com.stattrack.viewmodels.StatisticsActivityViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StatisticsActivity : AppCompatActivity() {

    private val dateValidator: DateValidator = DateValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        initializeUi()
    }

    @SuppressLint("NewApi")
    private fun initializeUi() {
        val statisticsActivityViewModel =
            ViewModelProvider(this).get(StatisticsActivityViewModel::class.java)
        statisticsActivityViewModel.init()
        val backButton = findViewById<Button>(R.id.back_to_main)
        val dayButton = findViewById<Button>(R.id.statisticsDay)
        val weekButton = findViewById<Button>(R.id.statisticsWeek)
        val monthButton = findViewById<Button>(R.id.statisticsMonth)
        val yearButton = findViewById<Button>(R.id.statisticsYear)
        val submitDateButton = findViewById<Button>(R.id.statisticsSubmitDateSelection)

        statisticsActivityViewModel.getActivityData().observe(this, { activityData ->
            val distance = findViewById<TextView>(R.id.statisticsDistance)
            val maxSpeed = findViewById<TextView>(R.id.statisticsMaxSpeed)
            val avgSpeed = findViewById<TextView>(R.id.statisticsAvgSpeed)
            distance.text = activityData.distance.toString().plus(" ").plus(Measures.DISTANCE.text)
            maxSpeed.text = activityData.maxSpeed.toString().plus(" ").plus(Measures.SPEED.text)
            avgSpeed.text = activityData.avgSpeed.toString().plus(" ").plus(Measures.SPEED.text)
        })

        statisticsActivityViewModel.getTemperatureData().observe(this, { temperatureData ->
            val minTemperature = findViewById<TextView>(R.id.statisticsMinTemperature)
            val avgTemperature = findViewById<TextView>(R.id.statisticsAvgTemperature)
            val maxTemperature = findViewById<TextView>(R.id.statisticsMaxTemperature)
            minTemperature.text = temperatureData.minimum.toString().plus(Measures.TEMPERATURE.text)
            avgTemperature.text = temperatureData.avg.toString().plus(Measures.TEMPERATURE.text)
            maxTemperature.text = temperatureData.maximum.toString().plus(Measures.TEMPERATURE.text)
        })

        backButton.setOnClickListener { onBackPressed() }

        dayButton.setOnClickListener {
            statisticsActivityViewModel.setActivityData(StatsPeriod.BY_DAY, LocalDate.now())
            statisticsActivityViewModel.setTemperatureData(StatsPeriod.BY_DAY, LocalDate.now())
        }

        weekButton.setOnClickListener {
            statisticsActivityViewModel.setActivityData(StatsPeriod.BY_WEEK, LocalDate.now())
            statisticsActivityViewModel.setTemperatureData(StatsPeriod.BY_WEEK, LocalDate.now())
        }

        monthButton.setOnClickListener {
            statisticsActivityViewModel.setActivityData(StatsPeriod.BY_MONTH, LocalDate.now())
            statisticsActivityViewModel.setTemperatureData(StatsPeriod.BY_MONTH, LocalDate.now())
        }

        yearButton.setOnClickListener {
            statisticsActivityViewModel.setActivityData(StatsPeriod.BY_YEAR, LocalDate.now())
            statisticsActivityViewModel.setTemperatureData(StatsPeriod.BY_YEAR, LocalDate.now())
        }

        submitDateButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.statisticsEnterDate)
            if (!dateValidator.validate(editText.text.toString())) {
                Toast.makeText(this, "Please enter a valid date!", Toast.LENGTH_SHORT).show()
            } else {
                editText.setText("")
                val date = LocalDate.parse(editText.text, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                statisticsActivityViewModel.setActivityData(StatsPeriod.BY_DAY, date)
                statisticsActivityViewModel.setTemperatureData(StatsPeriod.BY_DAY, date)
            }
        }
    }
}
