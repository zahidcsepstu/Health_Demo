package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BloodOxygen extends AppCompatActivity {

    CustomSeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_oxygen);
        seekBar= findViewById(R.id.custom_seek_bar_5);
        ProgressItem.initDataToSeekbar(seekBar);
    }
}