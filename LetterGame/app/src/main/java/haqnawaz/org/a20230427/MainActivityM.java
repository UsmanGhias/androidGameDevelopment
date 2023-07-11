package haqnawaz.org.a20230427;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivityM extends AppCompatActivity {

    Button skyBtn, grassBtn, rootBtn;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] rootLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] grassLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};

    TextView alphabet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m);

        alphabet = findViewById(R.id.alphabet);

       // int x = (int) (Math.random()%3);
        Random random = new Random();
        int category = random.nextInt(3);
        if(category == 0){
            int var = random.nextInt(skyLetters.length);
            alphabet.setText(String.valueOf(skyLetters[var]));
            //alphabet.setText("SkyLetter");

        }
        else if(category == 1){
            int var = random.nextInt(grassLetters.length);
            alphabet.setText(String.valueOf( grassLetters[var]));
          //  alphabet.setText("grassLetter");

        }
        else if(category == 2){
            int var = random.nextInt(rootLetters.length);
            alphabet.setText(String.valueOf(rootLetters[var]));
        }

        skyBtn= findViewById(R.id.button);
        skyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}