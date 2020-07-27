package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cunoraz.gifview.library.GifView;

import java.util.ArrayList;

public class BloodPressure1 extends AppCompatActivity {

    CustomSeekBar seekBar1,seekBar2;
    private float totalSpan = 1000;
    private float redSpan = 300;
    private float blueSpan = 300;
    private float greenSpan = 400;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure1);
        seekBar1= findViewById(R.id.custom_seek_bar_1);
        seekBar2= findViewById(R.id.custom_seek_bar_2);
        initDataToSeekbar();
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


        seekBar1.initData(progressItemList);
        seekBar1.invalidate();
        seekBar2.initData(progressItemList);
        seekBar2.invalidate();
    }

//    private ArrayList<BarEntry> dataValues(){
//        ArrayList<BarEntry> data = new ArrayList<>();
//        data.add(new BarEntry(0, new float[]{2,5.5f,4}));
//        data.add(new BarEntry(2, new float[]{2,6.5f,4}));
//        return  data;
//    }
}
