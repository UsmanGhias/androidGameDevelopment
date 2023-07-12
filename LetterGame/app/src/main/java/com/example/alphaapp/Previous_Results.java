package com.example.alphaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Previous_Results extends AppCompatActivity {

    TextView result, result2, result3, result4, result5, point, point2, point3, point4, point5;
    DbHelper db;

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_results);

        home = findViewById(R.id.button3);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Previous_Results.this, Drawer.class);
                startActivity(intent2);
            }
        });

        db = new DbHelper(Previous_Results.this);

        List<String> ans = db.getfiveResult();

        String answer = ans.get(0);
        String correct = ans.get(1);
        String ques = ans.get(2);

        String answer2 = ans.get(3);
        String correct2 = ans.get(4);
        String ques2 = ans.get(5);

        String answer3 = ans.get(6);
        String correct3 = ans.get(7);
        String ques3 = ans.get(8);




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


        int points2=0;
        for(int i=0; i<5; i++){
            if(answer2.charAt(i) == correct2.charAt(i)){
                points2+=1;
            }

        }

        result2=findViewById(R.id.result2);
        result2.setText("Letter: "+ ques2.charAt(0) + "\t Answer Given: " + answer2.charAt(0) + "\t Correct Answer: " + correct2.charAt(0) + "\n"+
                "Letter: "+ ques2.charAt(1) + "\t Answer Given: " + answer2.charAt(1) + "\t Correct Answer: " + correct2.charAt(1) + "\n"+
                "Letter: "+ ques2.charAt(2) + "\t Answer Given: " + answer2.charAt(2) + "\t Correct Answer: " + correct2.charAt(2) + "\n");


        point2 = findViewById(R.id.point2);
        point2.setText(points2 + "/5");


        int points3=0;
        for(int i=0; i<5; i++){
            if(answer3.charAt(i) == correct3.charAt(i)){
                points3+=1;
            }

        }

        result3=findViewById(R.id.result3);
        result3.setText("Letter: "+ ques3.charAt(0) + "\t Answer Given: " + answer3.charAt(0) + "\t Correct Answer: " + correct3.charAt(0) + "\n"+
                "Letter: "+ ques3.charAt(1) + "\t Answer Given: " + answer3.charAt(1) + "\t Correct Answer: " + correct3.charAt(1) + "\n"+
                "Letter: "+ ques3.charAt(2) + "\t Answer Given: " + answer3.charAt(2) + "\t Correct Answer: " + correct3.charAt(2) + "\n");


        point3 = findViewById(R.id.point3);
        point3.setText(points3 + "/5");




    }
}