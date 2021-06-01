package com.stattrack

import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.stattrack.models.Measures
import com.stattrack.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity()/*, SensorEventListener*/ {

    private var sensorManager: SensorManager? = null
//    private var totalSteps = 0f
//    private var running = false

    private lateinit var statisticsButton: Button
    private lateinit var chartsButton: Button
    private lateinit var mapButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        initializeUi()
//        println(Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID))
    }

    private fun initializeUi() {
        val mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.init()

        statisticsButton = findViewById(R.id.statistics)
        chartsButton = findViewById(R.id.charts)

        mainActivityViewModel.getActivityData().observe(this, { activityData ->
            val distance = findViewById<TextView>(R.id.mainActivityDistance)
            val avgSpeed = findViewById<TextView>(R.id.mainActivityAvgSpeed)
            distance.text = activityData.distance.toString().plus(" ").plus(Measures.DISTANCE.text)
            avgSpeed.text = activityData.avgSpeed.toString().plus(" ").plus(Measures.SPEED.text)
        })

        mainActivityViewModel.getTemperatureData().observe(this, { temperatureData ->
            val avgTemperature = findViewById<TextView>(R.id.mainActivityAvgTemperature)
            avgTemperature.text = temperatureData.avg.toString().plus(Measures.TEMPERATURE.text)
        })

        statisticsButton.setOnClickListener { openStatistics() }
        chartsButton.setOnClickListener { openCharts() }
    }

    private fun openStatistics() {
        startActivity(Intent(this, StatisticsActivity::class.java))
    }

    private fun openCharts() {
        startActivity(Intent(this, ChartsActivity::class.java))
    }

//    override fun onResume() {
//        super.onResume()
//
//        running = true
//        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
//        if (stepSensor == null) {
//            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
//        } else {
//            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)
//        }
//        val temperatureSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
//        if (temperatureSensor == null) {
//            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
//        } else {
//            sensorManager?.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_FASTEST)
//        }
//    }

//    override fun onSensorChanged(event: SensorEvent?) {
//        println(event!!.values[0])
//        println(running)
//        if (running) {
//            totalSteps = event.values[0]
//            val textView: TextView = findViewById(R.id.mainActivityDistance)
//            textView.text = ("$totalSteps")
//        }
//        val textView = findViewById<TextView>(R.id.mainActivityNow)
//        textView.text = (event!!.values[0].toString())
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//
//    }
}
 