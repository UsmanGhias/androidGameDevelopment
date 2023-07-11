package haqnawaz.org.a20230427;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LayoutDesigns extends AppCompatActivity {

    Button button, buttonGreen, buttonBlack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_designs);

       buttonBlack = findViewById(R.id.buttonBlack);
       buttonBlack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(LayoutDesigns.this, MainActivity.class);
               startActivity(intent);
           }
       });





        button = findViewById(R.id.buttonRed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LayoutDesigns.this, MainActivity.class);
                startActivity(intent);

                /*
                *         sentEmailBT.setOnClickListener(v -> {
            String subjectStr = subjectET.getText().toString();
            String messageStr = messageET.getText().toString();

            if (subjectStr.equals("")) {
                Toast.makeText(getApplicationContext(), "Please write your Subject", Toast.LENGTH_SHORT).show();
            } else if (messageStr.equals("")) {
                Toast.makeText(getApplicationContext(), "Please write your Message", Toast.LENGTH_SHORT).show();
            } else {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tariqsbadar@gmail.com", "kkaleemuddin@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectStr);
                emailIntent.putExtra(Intent.EXTRA_TEXT, messageStr);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        whatsAppLL.setOnClickListener(v -> {
            String url = "https://api.whatsapp.com/send?phone=+92 3344856560";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        callUsLL.setOnClickListener(v -> {
            String phoneNumber = "tel:" + "+92 3344856560";
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse(phoneNumber));
            startActivity(dialIntent);
        });

                * */
            }
        });

        buttonGreen = findViewById(R.id.buttonGreen);
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LayoutDesigns.this, MainActivity.class);
//                startActivity(intent);


                String phoneNumber = "tel:" + "+92 3344856560";
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse(phoneNumber));
                startActivity(dialIntent);


            }
        });




    }
}