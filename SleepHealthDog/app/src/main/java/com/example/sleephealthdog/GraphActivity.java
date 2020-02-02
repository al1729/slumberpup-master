package com.example.sleephealthdog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        //configureScore();
        //configureTime();
        //configureEfficiency();
        //get data!!
        graphtheData();
    }
    private void graphtheData(){
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }
    /*private void configureScore(){
        Button scoreButton = (Button) findViewById(R.id.score);
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change graphdata
            }
        });
    }
    private void configureTime(){
        Button timeButton = (Button) findViewById(R.id.time);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change graphdata
            }
        });
    }
    private void configureEfficiency(){
        Button efficButton = (Button) findViewById(R.id.effic);
        efficButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change graphdata
            }
        });
    }
    */
}
