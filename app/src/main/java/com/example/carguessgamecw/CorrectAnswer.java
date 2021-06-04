package com.example.carguessgamecw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class CorrectAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answer);
        getSupportActionBar().hide();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));



//        final Button identifyTheCarBrand = findViewById(R.id.ok_button);
//        identifyTheCarBrand.setOnClickListener(new View.OnClickListener() {    // this activity should close when the ok button is clicked
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}