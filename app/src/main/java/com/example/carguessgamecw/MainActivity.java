package com.example.carguessgamecw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {


    Switch timer;  //Adding timer by using Switch
    boolean isTimerSwitchOn = false;   //checking is the timer is on or off
    public static final String TIMER_ON = "countdown.status";

    private static final String LOG_TAG = MainActivity.class.getSimpleName();  //displaying a log message


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.switch_btn);  // assigning the switch button to variable timer

        //adding onCheckedChangeListener To change the boolean value when the switch button clicked
        timer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isTimerSwitchOn = true;
                }else{
                    isTimerSwitchOn = false;
                }
            }
        });
    }


    //method for Identify the Car Make button
    public void launchIdentifyBrandName(View view) {
        Log.d(LOG_TAG,"'Identify the Car Make' Button clicked!"); // displaying log message
        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, IdentifyBrandName.class);
        intent.putExtra("switchBool", isTimerSwitchOn); // the status of the timer is pass through an extra, whether it is on or not
        startActivity(intent);
    }

    //method for Hints button
    public void launchHints(View view) {
        Log.d(LOG_TAG,"'Hints' Button clicked!"); // displaying log message
        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, Hints.class);
        intent.putExtra("switchBool", isTimerSwitchOn); // the status of the timer is pass through an extra, whether it is on or not
        startActivity(intent);
    }

    //method for Identify the Car Image button
    public void launchIdentifyCarImage(View view) {
        Log.d(LOG_TAG,"'Identify the Car Image' Button clicked!"); // displaying log message
        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, IdentifyCarImage.class);
        intent.putExtra("switchBool", isTimerSwitchOn); // the status of the timer is pass through an extra, whether it is on or not
        startActivity(intent);
    }


    //method for Advanced Level button
    public void launchAdvancedLevel(View view) {
        Log.d(LOG_TAG,"'Advanced Level' Button clicked!"); // displaying log message
        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, AdvancedLevel.class);
        intent.putExtra("switchBool", isTimerSwitchOn); // the status of the timer is pass through an extra, whether it is on or not
        startActivity(intent);
    }

}