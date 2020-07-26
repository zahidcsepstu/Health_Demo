package com.example.healthdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

public class Self_Assesment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self__assesment);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_action_bar));
            actionBar.setTitle("Self Assesment");
        }

    }
}