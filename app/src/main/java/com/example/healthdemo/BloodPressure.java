package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setEnabled(false);
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
        data.add(new BarEntry(2, new float[]{2,6.5f,4}));
        return  data;
    }
}

