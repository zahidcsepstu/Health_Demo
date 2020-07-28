package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class BodyTemperature extends AppCompatActivity {
    CustomSeekBar seekBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_temperature);
        seekBar = findViewById(R.id.custom_seek_bar_4);
        ProgressItem.initDataToSeekbar(seekBar);
    }

}