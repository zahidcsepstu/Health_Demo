package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.cunoraz.gifview.library.GifView;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class BloodPressure extends AppCompatActivity {

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
        setContentView(R.layout.activity_blood_pressure);



        button=findViewById(R.id.button);
        seekBar=(CustomSeekBar)findViewById(R.id.custom_seek_bar);
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
        mProgressItem.progressItemPercentage = (greenSpan / totalSpan) * 100;
        mProgressItem.color = R.color.red;
        progressItemList.add(mProgressItem);


        seekBar.initData(progressItemList);
        seekBar.invalidate();
    }

//    private ArrayList<BarEntry> dataValues(){
//        ArrayList<BarEntry> data = new ArrayList<>();
//        data.add(new BarEntry(0, new float[]{2,5.5f,4}));
//        data.add(new BarEntry(2, new float[]{2,6.5f,4}));
//        return  data;
//    }
}

class ProgressItem {

    public int color;
    public float progressItemPercentage;
}


