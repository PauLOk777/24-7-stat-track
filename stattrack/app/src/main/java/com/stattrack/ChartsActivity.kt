package com.stattrack

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class ChartsActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var graph: GraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        backButton = findViewById(R.id.back_to_main)

        backButton.setOnClickListener { onBackPressed() }

        //Activity
        graph = findViewById(R.id.graph)
        val series: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(
                DataPoint(0.0, 1.1),
                DataPoint(1.0, 5.0),
                DataPoint(2.0, 3.0),
                DataPoint(3.0, 2.0),
                DataPoint(4.0, 6.0)

            )
        )
        graph.addSeries(series)

        //Temperature
        graph = findViewById(R.id.graph2)
        val series2: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(
                DataPoint(0.0, -3.0),
                DataPoint(1.0, 5.0),
                DataPoint(2.0, 10.0),
                DataPoint(3.0, -1.0),
                DataPoint(4.0, 6.0)
            )
        )
        graph.addSeries(series2)

    }
}
