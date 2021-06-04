package com.example.carguessgamecw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IdentifyBrandName extends AppCompatActivity {

    private String selectedCarBrand; // to store the selected brand from the dropdown
    private String carImageName;   //declaring the car image name
    private Button identifyButton; //declaring the identify Button to assign the "Identify" button
    private Spinner brandSpinner;  //declaring the brandSpinner to aasign the "drop down" spinner
    private ImageView carImageView;  //declaring the carImageView
    //private boolean clickedIdentify;  //clickedIdentify button to check Identify button is clicked or not
    private Integer imageCount;   //
    private TextView brandNameResult;
    private TextView displayCorrectAnswer;
    private TextView correctedBrandName;
    private CountDownTimer countDownTimer;  //setting the count down timer
    private boolean isTimerSwitchOn = false;  //to check the switch is on or off
    private TextView timer;  //setting the timer
    private String messageCorrectIncorrect;

    public static final String EXTRA_MESSAGE = null;
    //Creating a Array list to store
    public ArrayList<String> carBrandsList = new ArrayList<>(Arrays.asList("bmw","rolls_royce","mini","amg","mercedes_benz","smart",
            "alfa_romeo","fiat","maserati","lotus","proton","volvo","cadillac","chevrolet","saab","citroen","opel","vauxhall",
            "genesis","hyundai","kia","ferrari","mazda","tesla","dacia","infiniti","mitsubishi","audi","bentley","lamborgini"));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_brand_name);

        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");
        timer = findViewById(R.id.timer);

        carImageView = findViewById(R.id.display_car_images); //image of the car will displayed in this image view
        brandSpinner = findViewById(R.id.car_brand_names_spinner); //spinner is assign to brandSpinner
        brandNameResult = findViewById(R.id.display_correct_wrong);
        correctedBrandName = findViewById(R.id.display_answer);
        identifyButton = findViewById(R.id.submit_Brand); //Buttons is assigned to the identifyButton

        //---------Check whether switch is on or not---------//
        if (isTimerSwitchOn) {
            switchTimer();
            begin();
        } else {
            begin();
        }begin();
    }


    //----------------To handle timer--------------//
    private void switchTimer() {
        //----------------Create the countDownTimer and assigning time for 20 seconds----------------//
        countDownTimer = new CountDownTimer(21000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String seconds = millisUntilFinished / 1000 + "";
                timer.setText(seconds);
            }

            //---------------Method for when timer finish---------------//
            @Override
            public void onFinish() {
                autoSubmission();
            }
        };
        //------------Start the countdown----------//
        countDownTimer.start();
    }



    private void begin() {

        imageCount = 0;
        //getting the random car brand name from the array list - carBrandsList
        carImageName = getRandomBrand();
        String imageName = carImageName;
        carImageView.setImageDrawable(getResources().getDrawable(getBrandId(imageName,"drawable" , getApplicationContext())));

        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCarBrand = brandSpinner.getItemAtPosition(position).toString(); //getting the drop down value
                //Toast.makeText(IdentifyBrandName.this,"Selected" + selectedCarBrand,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.brand_names, android.R.layout.simple_spinner_item); // arrayAdapter with default spinner layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //layout is set to get list of drop down
        brandSpinner.setAdapter(adapter);

        identifyButton.setText("Identify");
    }

    public String getRandomBrand(){  //get a random car brand
        return carBrandsList.get(getRandomNumber(0,(carBrandsList.size()-1)));
    }

    public static int getRandomNumber(int min, int max) { // get a random integer value from a range
        return (new Random()).nextInt((max - min) + 1) + min;

    }

    //check if the name of the image matches with the images in drawable
    protected final static int getBrandId(final String imageName, final String imageType, final Context context) {
        final int brandId = context.getResources().getIdentifier(imageName, imageType, context.getApplicationInfo().packageName);
        if (brandId == 0) {
            throw new IllegalArgumentException("There is no car with " + imageName);
        }else{
            return brandId;
        }
    }


    //--------------------Auto submit when time is over-------------------//
    public void autoSubmission(){
        brandSpinner.setEnabled(false);
        identifyButton.setText("Next");
        //-----------Checking whether selected brand name is equal to car image-------------//
        if (selectedCarBrand.equals(carImageName) ){
            String answer = "CORRECT !";
            brandNameResult = findViewById(R.id.display_correct_wrong);
            brandNameResult.setTextColor(Color.GREEN);
            brandNameResult.setText(answer);
        }else {
            String answer = "WRONG !";
            brandNameResult = findViewById(R.id.display_correct_wrong);
            brandNameResult.setTextColor(Color.RED);
            brandNameResult.setText(answer);
            correctedBrandName = findViewById(R.id.display_answer);
            correctedBrandName.setTextColor(Color.YELLOW);
            correctedBrandName.setText(carImageName.toUpperCase() + " is correct");
        }
    }

    public void changeBtn(View view) {
        if (identifyButton.getText().equals("Identify")){
            brandSpinner.setEnabled(false);
            identifyButton.setText("Next");
            //-----------Checking whether selected brand name is equal to car image-------------//
            if (selectedCarBrand.equals(carImageName) ){
                String answer = "CORRECT !";
                brandNameResult = findViewById(R.id.display_correct_wrong);
                brandNameResult.setTextColor(Color.GREEN);
                brandNameResult.setText(answer);

                //---------Pause the timer when identify button is pressed---------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }

            }else {
                String answer = "WRONG !";
                String correct = "Correct Answer : ";
                brandNameResult = findViewById(R.id.display_correct_wrong);
                brandNameResult.setTextColor(Color.RED);
                brandNameResult.setText(answer);

                
                correctedBrandName = findViewById(R.id.display_answer);
                correctedBrandName.setTextColor(Color.YELLOW);
                correctedBrandName.setText( correct + carImageName.toUpperCase());

                //---------Pause the timer when identify button is pressed---------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }
            }
        }
        else {
            //--------------------When the button label is set to Next------------------//
            begin();
            //-------------------------Enable spinner-------------------------//
            brandSpinner.setEnabled(true);
            //-------------------------Reset timer-------------------------//
            if (isTimerSwitchOn){
                countDownTimer.cancel();
                switchTimer();
            }

            brandNameResult = findViewById(R.id.display_correct_wrong);
            brandNameResult.setText("");

            correctedBrandName = findViewById(R.id.display_answer);
            correctedBrandName.setText("");
        }

    }
}
