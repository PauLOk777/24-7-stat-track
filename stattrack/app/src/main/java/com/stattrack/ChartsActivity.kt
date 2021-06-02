package com.stattrack

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.stattrack.models.Measures
import com.stattrack.models.StatsPeriod
import com.stattrack.models.StatsPrecision
import com.stattrack.viewmodels.ChartsActivityViewModel
import java.time.LocalDate

class ChartsActivity : AppCompatActivity() {

    private lateinit var userId: String

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        initializeUi()

        userId = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
    }

    @SuppressLint("NewApi", "HardwareIds")
    private fun initializeUi() {
        val chartsActivityViewModel =
            ViewModelProvider(this).get(ChartsActivityViewModel::class.java)
        chartsActivityViewModel.init(Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID))
        val backButton = findViewById<Button>(R.id.back_to_main)
        val dayButton = findViewById<Button>(R.id.chartsDay)
        val weekButton = findViewById<Button>(R.id.chartsWeek)
        val monthButton = findViewById<Button>(R.id.chartsMonth)
        val yearButton = findViewById<Button>(R.id.chartsYear)

        val activityGraph = findViewById<GraphView>(R.id.chartsActivity)
        activityGraph.viewport.isXAxisBoundsManual = true
        activityGraph.legendRenderer.isVisible = true
        activityGraph.legendRenderer.align = LegendRenderer.LegendAlign.TOP
        chartsActivityViewModel.getStepsForChart().observe(this, { steps ->
            activityGraph.removeAllSeries()
            val offset = if (chartsActivityViewModel.getCurrentPrecision()
                == StatsPrecision.HOUR.text) 0 else 1
            val arr = Array(steps.size) { i -> DataPoint((i + offset).toDouble(), steps[i].toDouble()) }
            val seriesForActivityGraph: LineGraphSeries<DataPoint> = LineGraphSeries(arr)
            seriesForActivityGraph.title =
                chartsActivityViewModel.getCurrentPrecision().plus(",").plus(Measures.STEPS.text)
            activityGraph.addSeries(seriesForActivityGraph)
            activityGraph.viewport.setMinX(offset.toDouble())
            activityGraph.viewport.setMaxX(steps.size.toDouble())
        })

        val temperatureGraph = findViewById<GraphView>(R.id.chartsTemperature)
        temperatureGraph.viewport.isXAxisBoundsManual = true
        temperatureGraph.legendRenderer.isVisible = true
        temperatureGraph.legendRenderer.align = LegendRenderer.LegendAlign.TOP
        chartsActivityViewModel.getTemperatureForChart().observe(this, { temperatures ->
            temperatureGraph.removeAllSeries()
            val offset = if (chartsActivityViewModel.getCurrentPrecision()
                == StatsPrecision.HOUR.text) 0 else 1
            val listOfDataPoints = mutableListOf<DataPoint>()
            for (i in temperatures.indices) {
                if (temperatures[i] != null) {
                    listOfDataPoints.add(DataPoint((i + offset).toDouble(), temperatures[i]!!.toDouble()))
                }
            }

            val seriesForTemperatureGraph: LineGraphSeries<DataPoint> =
                LineGraphSeries(listOfDataPoints.toTypedArray())
            seriesForTemperatureGraph.title =
                chartsActivityViewModel.getCurrentPrecision().plus(",").plus(Measures.TEMPERATURE.text)
            temperatureGraph.addSeries(seriesForTemperatureGraph)
            temperatureGraph.viewport.setMinX(offset.toDouble())
            temperatureGraph.viewport.setMaxX(temperatures.size.toDouble())
        })

        dayButton.setOnClickListener {
            chartsActivityViewModel.setCurrentPrecision(StatsPrecision.HOUR)
            chartsActivityViewModel.setStepsForChart(
                StatsPrecision.HOUR,
                StatsPeriod.BY_DAY, System.currentTimeMillis()
            )
            chartsActivityViewModel.setTemperatureForChart(
                StatsPrecision.HOUR,
                StatsPeriod.BY_DAY, System.currentTimeMillis()
            )
        }

        weekButton.setOnClickListener {
            chartsActivityViewModel.setCurrentPrecision(StatsPrecision.DAY)
            chartsActivityViewModel.setStepsForChart(
                StatsPrecision.DAY,
                StatsPeriod.BY_WEEK, System.currentTimeMillis()
            )
            chartsActivityViewModel.setTemperatureForChart(
                StatsPrecision.DAY,
                StatsPeriod.BY_WEEK, System.currentTimeMillis()
            )
        }

        monthButton.setOnClickListener {
            chartsActivityViewModel.setCurrentPrecision(StatsPrecision.DAY)
            chartsActivityViewModel.setStepsForChart(
                StatsPrecision.DAY,
                StatsPeriod.BY_MONTH, System.currentTimeMillis()
            )
            chartsActivityViewModel.setTemperatureForChart(
                StatsPrecision.DAY,
                StatsPeriod.BY_MONTH, System.currentTimeMillis()
            )
        }

        yearButton.setOnClickListener {
            chartsActivityViewModel.setCurrentPrecision(StatsPrecision.MONTH)
            chartsActivityViewModel.setStepsForChart(
                StatsPrecision.MONTH,
                StatsPeriod.BY_YEAR, System.currentTimeMillis()
            )
            chartsActivityViewModel.setTemperatureForChart(
                StatsPrecision.MONTH,
                StatsPeriod.BY_YEAR, System.currentTimeMillis()
            )
        }

        backButton.setOnClickListener { onBackPressed() }
    }
}
