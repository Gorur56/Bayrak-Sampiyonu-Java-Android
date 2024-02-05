package com.firstapp.bayraksampiyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewDogru, textViewYanlis, textViewSoruSayi;
    private ImageView imageViewBayrak;
    private Button buttonA, buttonB, buttonC, buttonD;
=======

public class QuizActivity extends AppCompatActivity {

>>>>>>> cef995e8d1b3eab82d5a0ac7c8aa82bffb199dd9
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
<<<<<<< HEAD

        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruSayi = findViewById(R.id.textViewSoruSayi);

        imageViewBayrak = findViewById(R.id.imageViewBayrak);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
=======
>>>>>>> cef995e8d1b3eab82d5a0ac7c8aa82bffb199dd9
    }
}