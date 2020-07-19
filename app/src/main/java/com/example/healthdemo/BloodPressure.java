package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cunoraz.gifview.library.GifView;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BloodPressure extends AppCompatActivity {

    Button button;
    HorizontalBarChart barChart;
    int[] color=new int[]{Color.RED,Color.YELLOW,Color.GREEN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        button=findViewById(R.id.button);
        barChart=findViewById(R.id.bar_chart);
        BarDataSet barDataSet= new BarDataSet(dataValues(),"bar data set");
        barDataSet.setColors(color);
        BarData barData=new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getDescription().setEnabled(false);
        final GifView gifView=findViewById(R.id.gif2);

        gifView.play();





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gifView.isPaused()) {
                    gifView.play();
                }
                else {
                    gifView.pause();
                }
            }
        });
    }

    private ArrayList<BarEntry> dataValues(){
        ArrayList<BarEntry> data = new ArrayList<>();
        data.add(new BarEntry(0, new float[]{2,5.5f,4}));
        data.add(new BarEntry(1, new float[]{2,6.5f,4}));
        return  data;
    }
}
