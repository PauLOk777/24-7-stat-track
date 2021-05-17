package com.stattrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var statisticsButton: Button
    private lateinit var chartsButton: Button
    private lateinit var mapButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statisticsButton = findViewById(R.id.statistics)
        chartsButton = findViewById(R.id.charts)
        mapButton = findViewById(R.id.map)

        statisticsButton.setOnClickListener { openStatistics() }
        chartsButton.setOnClickListener { openCharts() }
        mapButton.setOnClickListener { openMap() }
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
}
 