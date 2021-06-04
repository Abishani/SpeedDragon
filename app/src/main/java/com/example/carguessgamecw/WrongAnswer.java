package com.example.carguessgamecw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WrongAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String message = intent.getStringExtra(IdentifyBrandName.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.correct_answer_for_the_ques_tv);    // get the correct answer which was past as an extra from the previous activity
        textView.setText(message);  // Setting the correct answer to the text View

        DisplayMetrics displayMatrics = new DisplayMetrics();      //getting details of current display
        getWindowManager().getDefaultDisplay().getMetrics(displayMatrics);

        int width = displayMatrics.widthPixels;
        int height = displayMatrics.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));        //setting the display to 80%


//        final Button identifyBtn = findViewById(R.id.ok_button_for_wrong_screen);
//        identifyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}