package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cunoraz.gifview.library.GifView;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.util.ArrayList;

public class BodyTemperature extends AppCompatActivity {

    Button button;
    HorizontalBarChart barChart;
    CustomSeekBar seekBar;
    private float totalSpan = 1000;
    private float redSpan = 300;
    private float blueSpan = 300;
    private float greenSpan = 400;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;
    int[] color=new int[]{Color.RED,Color.YELLOW,Color.GREEN};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_temperature);


        seekBar = findViewById(R.id.custom_seek_bar_4);
        initDataToSeekbar();

        // barChart=findViewById(R.id.bar_chart);
//        BarDataSet barDataSet= new BarDataSet(dataValues(),"bar data set");
//        barDataSet.setColors(color);
//        BarData barData=new BarData(barDataSet);
//        barChart.setData(barData);
//        barChart.getAxisLeft().setEnabled(false);
//        barChart.getAxisRight().setDrawGridLines(false);
//        barChart.getXAxis().setDrawGridLines(false);
//        barChart.getDescription().setEnabled(false);
//        barChart.getAxisRight().setEnabled(false);
//        barChart.getXAxis().setEnabled(false);

    }

    private void initDataToSeekbar() {
        progressItemList = new ArrayList<ProgressItem>();
        // red span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = ((redSpan / totalSpan) * 100);
        Log.i("Mainactivity", mProgressItem.progressItemPercentage + "");
        mProgressItem.color = R.color.yellow;
        progressItemList.add(mProgressItem);
        // blue span
        mProgressItem = new ProgressItem();
        mProgressItem.progressItemPercentage = (blueSpan / totalSpan) * 100;
        mProgressItem.color = R.color.green;
        progressItemList.add(mProgressItem);
        // green span
        mProgressItem = new ProgressItem();
        float greenSpan = 400;
        mProgressItem.progressItemPercentage = (greenSpan / totalSpan) * 100;
        mProgressItem.color = R.color.red;
        progressItemList.add(mProgressItem);


        seekBar.initData(progressItemList);
        seekBar.invalidate();
    }

}