package haqnawaz.org.a20230427;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView letterTextView, answerTextView;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] grassLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] rootLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private String answerString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letterTextView = findViewById(R.id.letter_text_view);
        letterTextView.setText(getRandomLetter());

        answerTextView = findViewById(R.id.answer_text_view);

        Button skyButton = findViewById(R.id.sky_button);
        skyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerString == "Sky Letter") {
                    answerTextView.setText("Awesome your answer is right");
                } else {
                    answerTextView.setText("Incorrect! the answer is " + answerString);
                }

                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                        answerTextView.setText("");
                    }
                }, 5000); // 5000 milliseconds = 5 seconds
            }
        });

        Button grassButton = findViewById(R.id.grass_button);
        grassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerString == "Grass Letter") {
                    answerTextView.setText("Awesome your answer is right");
                } else {
                    answerTextView.setText("Incorrect! the answer is " + answerString);
                }
                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                        answerTextView.setText("");
                    }
                }, 5000); // 5000 milliseconds = 5 seconds
            }
        });

        Button rootButton = findViewById(R.id.root_button);
        rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerString == "Root Letter") {
                    answerTextView.setText("Awesome your answer is right");
                } else {
                    answerTextView.setText("Incorrect! the answer is " + answerString);
                }
                // Wait for 5 seconds and create a new question
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        letterTextView.setText(getRandomLetter());
                        answerTextView.setText("");
                    }
                }, 5000); // 5000 milliseconds = 5 seconds
            }
        });
    }

    private String getRandomLetter() {
        Random random = new Random();
        int category = random.nextInt(3);
        char letter;
        switch (category) {
            case 0:
                letter = skyLetters[random.nextInt(skyLetters.length)];
                answerString = "Sky Letter";
                break;
            case 1:
                letter = grassLetters[random.nextInt(grassLetters.length)];
                answerString = "Grass Letter";
                break;
            default:
                letter = rootLetters[random.nextInt(rootLetters.length)];
                answerString = "Root Letter";
                break;
        }
        return String.valueOf(letter);
    }
}
