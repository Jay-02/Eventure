package com.example.eventure.view;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventure.R;

public class choose extends AppCompatActivity {

    ImageView fav1, fav2, fav3, fav4, fav5, fav6;
    TextView txt1, txt2, txt3, txt4, txt5, txt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fav1 = findViewById(R.id.fav1);
        fav2 = findViewById(R.id.fav2);
        fav3 = findViewById(R.id.fav3);
        fav4 = findViewById(R.id.fav4);
        fav5 = findViewById(R.id.fav5);
        fav6 = findViewById(R.id.fav6);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);

        fav1.setOnClickListener(v -> sendData("Music"));
        fav2.setOnClickListener(v -> sendData("Camping"));
        fav3.setOnClickListener(v -> sendData("Art"));
        fav4.setOnClickListener(v -> sendData("Adventure"));
        fav5.setOnClickListener(v -> sendData("Sport"));
        fav6.setOnClickListener(v -> sendData("Cooking"));
    }

    private void sendData(String interest) {
        Intent intent = new Intent(choose.this, MainActivity.class);
        intent.putExtra("interest", interest);
        startActivity(intent);
}
}