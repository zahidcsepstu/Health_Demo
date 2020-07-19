package com.example.healthdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.time.Duration;
import java.util.ArrayList;

public class History extends AppCompatActivity {
    LineChart lineChart;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        calendarView = findViewById(R.id.calender);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });


        lineChart=findViewById(R.id.line_chart2);
        LineDataSet lineDataSet= new LineDataSet(dataSets(),"label");
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(lineDataSet);

        LineDataSet lineDataSet1= new LineDataSet(dataSets1(),"label");
        dataSets.add(lineDataSet1);

        LineData data =new LineData(dataSets);
        lineChart.setData(data);

        data.setValueTextSize(12);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new MyAxisValueFormatter());



        lineChart.invalidate();
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);


        lineChart.getAxisLeft().setDrawLabels(false);
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getXAxis().setDrawLabels(true);


        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setDrawBorders(false);





    }

    private ArrayList<Entry> dataSets(){
        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(160,12));
        data.add(new Entry(190,15));
        data.add(new Entry(200,13));
        data.add(new Entry(210,15));
        return  data;
    }

    private ArrayList<Entry> dataSets1(){
        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(160,12+10));
        data.add(new Entry(190,15+10));
        data.add(new Entry(200,13+10));
        data.add(new Entry(210,15+10));
        return  data;
    }

    private static class MyAxisValueFormatter extends ValueFormatter{
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public String getFormattedValue(float value) {
            int value1= (int) value;
            Duration duration = Duration.ofMinutes(Long.parseLong(String.valueOf(value1)));
            long hours = duration.toHours();
            long mins = duration.minusHours(hours).toMinutes();
            @SuppressLint("DefaultLocale") String formatted = String.format("%d: %02d", hours, mins);
            return formatted;
        }
    }
}



