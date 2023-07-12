package com.example.alphaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView letterTextView, answerTextView, s;
    Button submit, res;
    DbHelper db;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] rootLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] grassLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private String answerString = "";

    private String correct="";
    private String ans = "";

    private String ques = "";
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        submit = findViewById(R.id.submit);
        submit.setVisibility(View.GONE);

        res = findViewById(R.id.button2);
        res.setVisibility(View.GONE);

        letterTextView = findViewById(R.id.letter_text_view);
        letterTextView.setText(getRandomLetter());

       // answerTextView = findViewById(R.id.answer_text_view);

        Button skyButton = findViewById(R.id.sky_button);
        skyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans+='0';
                if (answerString == "Sky Letter") {
                   // answerTextView.setText("Awesome your answer is right");
                } else {
                    //answerTextView.setText("Incorrect! the answer is " + answerString);
                }

                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                       // answerTextView.setText("");
                    }
                }, 1000); // 5000 milliseconds = 5 seconds
            }
        });

        Button grassButton = findViewById(R.id.grass_button);
        grassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans+='1';
                if (answerString == "Grass Letter") {
                   // answerTextView.setText("Awesome your answer is right");
                } else {
                   // answerTextView.setText("Incorrect! the answer is " + answerString);
                }
                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                       // answerTextView.setText("");
                    }
                }, 1000); // 5000 milliseconds = 5 seconds
            }
        });

        Button rootButton = findViewById(R.id.root_button);
        rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans+='2';
                if (answerString == "Root Letter") {
                   // answerTextView.setText("Awesome your answer is right");
                } else {
                   // answerTextView.setText("Incorrect! the answer is " + answerString);
                }
                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                        //answerTextView.setText("");
                    }
                }, 1000); // 5000 milliseconds = 5 seconds
            }
        });
    }

    private String getRandomLetter() {
        if (counter == 5) {
            submit.setVisibility(View.VISIBLE);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db = new DbHelper(MainActivity.this);
                    db.insert(ans, correct, ques);
                    s = findViewById(R.id.submission);
                    s.setText("Your test is submitted");

                    res.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.GONE);
                    res.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent2 = new Intent(MainActivity.this, Result.class);
                            startActivity(intent2);
                        }
                    });

                }
            });
        } else {
            counter = counter + 1;
            Random random = new Random();
            int category = random.nextInt(3);
            char letter;
            switch (category) {
                case 0:
                    letter = skyLetters[random.nextInt(skyLetters.length)];
                    answerString = "Sky Letter";
                    correct+='0';
                    break;
                case 1:
                    letter = grassLetters[random.nextInt(grassLetters.length)];
                    answerString = "Grass Letter";
                    correct+='1';
                    break;
                default:
                    letter = rootLetters[random.nextInt(rootLetters.length)];
                    answerString = "Root Letter";
                    correct+='2';
                    break;
            }
            ques=ques+String.valueOf(letter);
            return String.valueOf(letter);
        }

        return null;
    }
}
