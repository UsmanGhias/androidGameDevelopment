package com.example.alphaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Result extends AppCompatActivity {

    TextView result, point;
    DbHelper db;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        home = findViewById(R.id.button3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Result.this, Drawer.class);
                startActivity(intent2);
            }
        });

        db = new DbHelper(Result.this);

        List<String> ans = db.getResult();

        String answer = ans.get(0);
        String correct = ans.get(1);
        String ques = ans.get(2);

        int points=0;
        for(int i=0; i<5; i++){
            if(answer.charAt(i) == correct.charAt(i)){
                points+=1;
            }

        }

        result=findViewById(R.id.result);
        result.setText("Letter: "+ ques.charAt(0) + "\t Answer Given: " + answer.charAt(0) + "\t Correct Answer: " + correct.charAt(0) + "\n"+
                "Letter: "+ ques.charAt(1) + "\t Answer Given: " + answer.charAt(1) + "\t Correct Answer: " + correct.charAt(1) + "\n"+
                "Letter: "+ ques.charAt(2) + "\t Answer Given: " + answer.charAt(2) + "\t Correct Answer: " + correct.charAt(2) + "\n");


        point = findViewById(R.id.point);
        point.setText(points + "/5");


    }
}