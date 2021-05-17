package com.stattrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChartsActivity : AppCompatActivity() {

    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        backButton = findViewById(R.id.back_to_main)

        backButton.setOnClickListener { backToMain() }
    }

    private fun backToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
