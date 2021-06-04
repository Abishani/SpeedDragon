package com.example.carguessgamecw;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carguessgamecw.R;

import static android.view.View.VISIBLE;

public class Hints extends AppCompatActivity {
    //------Declaration of imageView------//
    private ImageView hint_image;
    //------Maximum image guess------//
    private final int max_guess = 3;
    //------Declaration of button submit------//
    private Button buttonSubmit;
    //------Declaration of editText------//
    private EditText editTextHint;
    //------Declaration of hintLetter textView------//
    private TextView hintLetters_tv;
    //------Declaration of correctCar textView------//
    private TextView displayCorrectCarName;
    //------Declaration of correctLabel textView------//
    private TextView correctOrWrong;
    //------Declaration of timer------//
    private TextView timer;
    //------Declaration of counterTimer------//
    private CountDownTimer countDownTimer;
    //------Declaration of last random------//
    private int random;
    //-----------To see the answer------------//
    private String answerDisplay;
    //---------Store the underscore value in the textView-----------//
    private String letterClue;
    //-----------Store attempt count-----------//
    private int attemptCount = 0;
    //-----------Declaration of Car class---------//
    private Database car;
    private boolean isTimerSwitchOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        Intent intent = getIntent();
        //-------------To get the boolean value from main activity-------------//
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");

        //---------Check whether switch is on or not---------//
        if (isTimerSwitchOn) {
            switchTimer();
            begin();
        } else {
            begin();
        }
    }

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
                correctOrWrong = findViewById(R.id.result_textview);
                String notAnswered = "NOT ANSWERED";
                correctOrWrong.setTextColor(Color.GRAY);
                correctOrWrong.setText(notAnswered);
            }
        };
        //--------------------To Start the countdown--------------------//
        countDownTimer.start();
    }

    //-----------Declaring Views by its Id----------------//
    public void findViews() {
        hint_image = findViewById(R.id.display_car_images);
        hintLetters_tv = findViewById(R.id.blanks_textview);
        buttonSubmit = findViewById(R.id.submit_btn);
        editTextHint = findViewById(R.id.enter_character);
        displayCorrectCarName = findViewById(R.id.display_correct_answer);
        correctOrWrong = findViewById(R.id.result_textview);
        timer = findViewById(R.id.timer);
    }

    //-----------To store the answer--------//
    public void storeCarBrandAnswer() {
        random = Database.getLastRandomIndex();
        answerDisplay = car.getBrandName(random);
        answerDisplay = answerDisplay.toUpperCase();
    }


    //-----------To set the underScore--------//
    public void setUnderscores() {
        letterClue = "";
        //----------Display underScore-----------//
        for (int i = 0; i < answerDisplay.length(); i++) {
            if (!(answerDisplay.charAt(i) == (' '))) {
                letterClue = letterClue + "_ ";
            } else
                letterClue = letterClue + "   ";
        }
        System.out.println(hintLetters_tv.toString());
        System.out.println(letterClue);
        hintLetters_tv.setText(letterClue);
        hintLetters_tv.setVisibility(VISIBLE);
    }


    //-----------
    public void begin() {

        car = new Database();

        //-------------Calling the findView method--------//
        findViews();

        //-------------Set random image----------//
        hint_image.setImageResource(car.getRandomCar());
        hint_image.setVisibility(VISIBLE);

        //------------Calling the storeCarBrandAnswer----------//
        storeCarBrandAnswer();

        //------------Calling underscore method to display--------------//
        setUnderscores();


        //REFERENCE: https://stackoverflow.com/questions/13593069/androidhide-keyboard-after-button-click
        //------------------To display keyboard onscreen --------------//
        final EditText editText_wordHint = findViewById(R.id.enter_character);
        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        editText_wordHint.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    inputMethodManager.showSoftInput(editText_wordHint, InputMethodManager.SHOW_IMPLICIT);
                    editText_wordHint.setHint("");
                } else
                    editText_wordHint.setHint("Enter a Letter!");
            }
        });
    }


    public void submitButton(View v) {
        //REFERENCE:https://stackoverflow.com/questions/13593069/androidhide-keyboard-after-button-click
        //----------------Hide Virtual Key Board When  Clicking Button---------------//
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(buttonSubmit.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        boolean found = false;

        //------------------Change submit button to next and get to next page----------------//
        if (buttonSubmit.getText().equals(getString(R.string.next))) {
            correctOrWrong.setVisibility(View.INVISIBLE);
            newCar();
        } else if ((editTextHint.getText().toString().equals("")
                || (editTextHint.getText().toString().equals(getString(R.string.enter_a_letter))))) {
        } else {
            //-----------Get the last char of editText to insert only one letter to the edit text---------//
            char guess = editTextHint.getText().toString().toUpperCase().charAt(0);
            StringBuilder newClue = new StringBuilder(letterClue);
            //------To change the answer with the same amount of spaces as there is in String clue-------//
            String answerLetterRemake = "";


            for (int i = 0; i < answerDisplay.length(); i++) {
                //-----------if there is an empty space,put there empty space---------//
                if (answerDisplay.charAt(i) == ' ') {
                    answerLetterRemake = answerLetterRemake + "   ";
                } else if (answerDisplay.charAt(i) == ',') {
                    answerLetterRemake = answerLetterRemake + ",";
                } else {
                    //---------remake = answer + space-------------//
                    answerLetterRemake = answerLetterRemake + answerDisplay.charAt(i) + " ";
                }
            }

            //------------for the length of the clue, if answerRemake at specific char equals the guessed letter-------------//
            for (int j = 0; j < letterClue.length(); j++) {
                if (answerLetterRemake.charAt(j) == guess) {
                    //------------reshape newClue with the letter----------------//
                    newClue.setCharAt(j, guess);
                    found = true;
                }
            }

            //----------when user enters correct letter,it does not display that guess is incorrect---------//
            if (found) {
                correctOrWrong.setText("");
                correctOrWrong.setVisibility(View.INVISIBLE);
            }
            //-----------------if it was guess was correct, change the answerLabel to display the guess is incorrect---------//
            else {
                //--------------------if it was not found, count it as wrong attempt--------------------//
                attemptCount++;
                correctOrWrong.setTextColor(Color.rgb(173, 237, 255));
                correctOrWrong.setText(getString(R.string.incorrect_guess));
                correctOrWrong.setVisibility(VISIBLE);
            }

            //----------------Exporting out of the function so modified clue can be accessed by another guessing action-------------//
            letterClue = newClue.toString();
            hintLetters_tv.setText(letterClue);
            editTextHint.setText("");

            //----------------if all underscore are found, change textView to CORRECT! in green and change button----------------//
            if (!(letterClue.contains("_"))) {
                hintLetters_tv.setText(getString(R.string.CORRECT));
                hintLetters_tv.setTextColor(Color.rgb(0, 255, 0));
                editTextHint.setVisibility(View.INVISIBLE);
                buttonSubmit.setText(getString(R.string.next));
            }

            //--------------if all the attempts are used, change to WRONG in red and change button-------------//
            if (attemptCount >= max_guess) {
                guessesUsed();
            }
        }
    }


    //
    public void newCar() {
        editTextHint.setVisibility(VISIBLE);
        buttonSubmit.setText(getString(R.string.SUBMIT));
        attemptCount = 0;
        displayCorrectCarName.setVisibility(View.INVISIBLE);
        hintLetters_tv.setTextColor(Color.rgb(255, 64, 129));
        begin();
    }

    public void guessesUsed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                displayCorrectCarName.setText(answerDisplay);
                displayCorrectCarName.setVisibility(VISIBLE);
                hintLetters_tv.setText(getString(R.string.WRONG));
                hintLetters_tv.setTextColor(Color.rgb(255, 0, 0));
                editTextHint.setVisibility(View.INVISIBLE);
                buttonSubmit.setText(getString(R.string.next));
            }
        });
    }

}