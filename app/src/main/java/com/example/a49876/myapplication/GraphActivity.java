package com.example.a49876.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Example graph.
 */

public class GraphActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        // Creates a graph
        GraphView graph = findViewById(R.id.graph);

        // The points to plot on the graph
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 15),
                new DataPoint(1, 30),
                new DataPoint(2, 60),
                new DataPoint(3, 120),
                new DataPoint(4, 150),
                new DataPoint(5, 20)
        });
        graph.addSeries(series);
    }
}
