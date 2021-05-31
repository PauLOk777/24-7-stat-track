package com.stattrack

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var totalSteps = 0f
    private var running = false

    private lateinit var statisticsButton: Button
    private lateinit var chartsButton: Button
    private lateinit var mapButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        statisticsButton = findViewById(R.id.statistics)
        chartsButton = findViewById(R.id.charts)
        mapButton = findViewById(R.id.map)

        statisticsButton.setOnClickListener { openStatistics() }
        chartsButton.setOnClickListener { openCharts() }
        mapButton.setOnClickListener { openMap() }

        println(Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID))
    }

    private fun openStatistics() {
        startActivity(Intent(this, StatisticsActivity::class.java))
    }

    private fun openCharts() {
        startActivity(Intent(this, ChartsActivity::class.java))
    }

    private fun openMap() {
        startActivity(Intent(this, MapActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        val textView: TextView = findViewById(R.id.textView6)
        textView.text = ("0")
        if (stepSensor == null) {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        println(event!!.values[0])
        println(running)
        if (running) {
            totalSteps = event.values[0]
            val textView: TextView = findViewById(R.id.textView6)
            textView.text = ("$totalSteps")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println("onAccuracyChanged: Sensor: $sensor; accuracy: $accuracy")
    }
}
 