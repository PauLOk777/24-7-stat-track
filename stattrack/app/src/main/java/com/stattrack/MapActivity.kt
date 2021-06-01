package com.stattrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val backButton = findViewById<Button>(R.id.back_to_main)
        backButton.setOnClickListener { onBackPressed() }
    }
}
