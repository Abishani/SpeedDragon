package com.example.carguessgamecw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IdentifyCarImage extends AppCompatActivity {

    private String carImage1;
    private String carImage2;
    private String carImage3;
    private TextView brandNameToGuess;
    private TextView correctIncorrect;
    private ImageView carImageView1, carImageView2, carImageView3;
    private String pickedBrandImgName;
    private Integer select;
//    private Integer imageCount;
    private CountDownTimer countDownTimer;
    private boolean isTimerSwitchOn = false;
    private TextView timerTextView;


    private ArrayList<String> selectedRandomBrands;

    private int numberOfCars = 30; // number of car brands
    //initialize the brands to a array list
    public ArrayList<String> carBrandsList = new ArrayList<>(Arrays.asList("bmw","rolls_royce","mini","amg","mercedes_benz","smart",
            "alfa_romeo","fiat","maserati","lotus","proton","volvo","cadillac","chevrolet","saab","citroen","opel","vauxhall",
            "genesis","hyundai","kia","ferrari","mazda","tesla","dacia","infiniti","mitsubishi","audi","bentley","lamborgini"));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_image);

        brandNameToGuess = findViewById(R.id.brand_textView); //the TextView view is taken from the layout.xml and assign it to the breedNameToGuess
        correctIncorrect = findViewById(R.id.display_correct_wrong); //the TextView view is taken from the layout.xml and assign it to the messageCorrectIncorrect
        carImageView1 = findViewById(R.id.car_imageView1); //the 1st ImageView is taken from the layout.xml and assign it to the firstImg
        carImageView2 = findViewById(R.id.car_imageView2); ///the 2nd ImageView view is taken from the layout.xml and assign it to the secondImg
        carImageView3 = findViewById(R.id.car_imageView3); //the 3rd ImageView view is taken from the layout.xml and assign it to the thirdImg
        timerTextView = findViewById(R.id.timer); // Timer

        Intent intent = getIntent();
        //---------To get the boolean value from main activity---------//
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");

        //---------Check whether switch is on or not---------//
        if (isTimerSwitchOn) {
            switchTimer();
            begin();
        } else {
            begin();
        }

    }


    //----------------To handle timer--------------//
    private void switchTimer() {
        //----------------Create the countDownTimer and assigning time for 20 seconds----------------//
        countDownTimer = new CountDownTimer(21000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String seconds = millisUntilFinished / 1000 + "";
                timerTextView.setText(seconds);
            }

            //---------------Method for when timer finish---------------//
            @Override
            public void onFinish() {
//                imageCount++;
                correctIncorrect = findViewById(R.id.display_correct_wrong);
                correctIncorrect.setTextColor(Color.RED);
                correctIncorrect.setText("NOT ANSWERED");
            }
        };
        //------------Start the countdown----------//
        countDownTimer.start();
    }


    public void begin(){
        select =  0;

        selectedRandomBrands = getBrandsRandom(); //assigning the 3 unique breeds to a array list
        pickedBrandImgName = selectedRandomBrands.get(getRandomNumber(0,2)); //select a breed (1 out of 3) from the array list and for this breed we are finding image

        carImage1 = selectedRandomBrands.get(0) ; // the first element (breed) from the array list and a random image( a random id ) from the breed is taken
        carImageView1.setImageDrawable( getResources().getDrawable(getBrandNo(carImage1, "drawable", getApplicationContext()))); // image is taken from the drawable file

        carImage2 = selectedRandomBrands.get(1) ;  // the second element (breed) from the array list and a random image( a random id ) from the breed is taken
        carImageView2.setImageDrawable( getResources().getDrawable(getBrandNo(carImage2, "drawable", getApplicationContext()))); // image is taken from the drawable file

        carImage3 = selectedRandomBrands.get(2) ; // the third element (breed) from the array list and a random image( a random id ) from the breed is taken
        carImageView3.setImageDrawable( getResources().getDrawable(getBrandNo(carImage3, "drawable", getApplicationContext()))); // image is taken from the drawable file

        brandNameToGuess.setText(pickedBrandImgName.toUpperCase()); //displaying the selected brand in a text view
    }

    protected final static int getBrandNo(final String imageName, final String imageType, final Context context) {
        final int imageCar = context.getResources().getIdentifier(imageName, imageType, context.getApplicationInfo().packageName);
        if (imageCar == 0) {
            throw new IllegalArgumentException("Image is not found with a Name " + imageName);

        } else {
            return imageCar;
        }

    }

    public ArrayList<String> getBrandsRandom(){

        ArrayList<String> uniqueBrandList = new ArrayList<>(); // getting 3 brands from breedListArray and checking is it available in the array list and then add them to the array list
        while(uniqueBrandList.size()!=3){ //only take 3 Unique brands
            String pickedBrand= getABrandRandom(); // take a brand
            if(uniqueBrandList.indexOf(pickedBrand) == -1){ // check if the brand exist in the  list
                uniqueBrandList.add(pickedBrand);      // add the unique brand to the array list
            }
        }
        return uniqueBrandList; //the list return 3 unique brands
    }

    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;

    }

    public String getABrandRandom(){ //get a random brand
        return carBrandsList.get(getRandomNumber(0,(carBrandsList.size()-1))); //get a random brand from carBrandsList
    }

    //method to the selection of first image
    public void OnClickFirstImage(View view){

//        imageCount++;
        String currentAnswer;           // hold the result (is the seleted item is true or false)
        select = select +1;     //increase the count when the user select the image
        if(select == 1) {    //display the status(correct/wrong) only when the image is selected (by select which is count)
            if (pickedBrandImgName.equals(carImage1)) { //check if the breed name is equal to the image name
                currentAnswer = "CORRECT!";    // if above condition true correct is initialize
                correctIncorrect.setTextColor(Color.parseColor("#008000")); //display text in green colour
                correctIncorrect.setText(currentAnswer.toUpperCase());       // display the status

                //-----------------To pause the timer-----------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }


            } else {
                currentAnswer = "WRONG!"; // if above condition false WRONG is initialize
                correctIncorrect.setTextColor(Color.parseColor("#FF0000")); //display text in red colour
                correctIncorrect.setText(currentAnswer.toUpperCase());  // display the status
                carImageView1.setBackgroundColor(Color.parseColor("#FF0000")); // the background colour of the image is change red to show the selected status is wrong
                colourCorrectBrand(); // find the correct image and background colour is change to green

                //--------------Pause Timer------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }
            }
        }else {
            Toast.makeText(this, "Click Next To Continue",Toast.LENGTH_SHORT).show(); //after the image is selected the only option is to select next
        }
    }


    //method to the selection of second image
    public void OnClickSecondImage(View view){
//        imageCount++;
        String currentAnswer;           // hold the result (is the seleted item is true or false)
        select = select +1;     //increase the count when the user select the image
        if(select== 1) {    //display the status(correct/wrong) only when the image is selected (by select which is count)
            if (pickedBrandImgName.equals(carImage2)) { //check if the breed name is equal to the image name
                currentAnswer = "CORRECT!";    // if above condition true correct is initialize
                correctIncorrect.setTextColor(Color.parseColor("#008000")); //display text in green colour
                correctIncorrect.setText(currentAnswer.toUpperCase());       // display the status

                //-----------------To pause the timer-----------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }


            } else {
                currentAnswer = "WRONG!"; // if above condition false WRONG is initialize
                correctIncorrect.setTextColor(Color.parseColor("#FF0000")); //display text in red colour
                correctIncorrect.setText(currentAnswer.toUpperCase());  // display the status
                carImageView1.setBackgroundColor(Color.parseColor("#FF0000")); // the background colour of the image is change red to show the selected status is wrong
                colourCorrectBrand(); // find the correct image and background colour is change to green

                //-----------------To pause the timer-----------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }
            }
        }else {
            Toast.makeText(this, "Click Next To Continue",Toast.LENGTH_SHORT).show(); //after the image is selected the only option is to select next
        }
    }




    //method to the selection of third image
    public void OnClickThirdImage(View view){
//        imageCount++;
        String currentAnswer;           // hold the result (is the seleted item is true or false)
        select = select +1;     //increase the count when the user select the image
        if(select== 1) {    //display the status(correct/wrong) only when the image is selected (by select which is count)
            if (pickedBrandImgName.equals(carImage3)) { //check if the breed name is equal to the image name
                currentAnswer = "CORRECT!";    // if above condition true correct is initialize
                correctIncorrect.setTextColor(Color.parseColor("#008000")); //display text in green colour
                correctIncorrect.setText(currentAnswer.toUpperCase());       // display the status

                //-----------------To pause the timer-----------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }


            } else {
                currentAnswer = "WRONG!"; // if above condition false WRONG is initialize
                correctIncorrect.setTextColor(Color.parseColor("#FF0000")); //display text in red colour
                correctIncorrect.setText(currentAnswer.toUpperCase());  // display the status
                carImageView1.setBackgroundColor(Color.parseColor("#FF0000")); // the background colour of the image is change red to show the selected status is wrong
                colourCorrectBrand(); // find the correct image and background colour is change to green

                //-----------------To pause the timer-----------------//
                if (isTimerSwitchOn) {
                    countDownTimer.cancel();
                }
            }
        }else {
            Toast.makeText(this, "Click Next To Continue",Toast.LENGTH_SHORT).show(); //after the image is selected the only option is to select next
        }
    }





    public void colourCorrectBrand(){
        String firstBrandName = carImage1;
        String secondBrandName = carImage2;
        String thirdBrandName = carImage3;

        if (pickedBrandImgName.equals(firstBrandName)){ // if the image name is found equal to breed name the image background is coloured with green
            carImageView1.setBackgroundColor(Color.parseColor("#008000"));
        }

        if (pickedBrandImgName.equals(secondBrandName)){
            carImageView2.setBackgroundColor(Color.parseColor("#008000"));
        }

        if(pickedBrandImgName.equals(thirdBrandName)){
            carImageView3.setBackgroundColor(Color.parseColor("#008000"));
        }
    }

    public void OnClickNextButton(View view){ //method for the Next is selected
        if (select != 0) {  // select is already counted if the above image is selected
            begin(); //now advance to a next screen
            correctIncorrect.setText(""); //initialize the status black in the next screen

            carImageView1.setBackgroundColor(Color.parseColor("#000000")); //initial the background color of the images
            carImageView2.setBackgroundColor(Color.parseColor("#000000"));
            carImageView3.setBackgroundColor(Color.parseColor("#000000"));
        }else {
            Toast.makeText(this, "Click A car image", Toast.LENGTH_SHORT).show();
        }
    }

}