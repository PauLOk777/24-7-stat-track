package com.stattrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.stattrack.R

class StatisticsActivity : AppCompatActivity() {

    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        backButton = findViewById(R.id.back_to_main)
        backButton.setOnClickListener { onBackPressed() }
    }
}
