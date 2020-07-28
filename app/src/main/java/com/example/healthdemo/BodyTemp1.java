package com.example.healthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BodyTemp1 extends AppCompatActivity {
    CustomSeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_temp1);
        seekBar = findViewById(R.id.custom_seek_bar_7);
        ProgressItem.initDataToSeekbar(seekBar);
    }
}