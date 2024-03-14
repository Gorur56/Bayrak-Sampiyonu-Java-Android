package com.firstapp.bayraksampiyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewSonuc, textViewYuzdeSonuc;
    private Button buttonTekrar;

    private int dogruSayac;
    private int yanlisSonuc;
    private  int toplamSoru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewSonuc = findViewById(R.id.textViewSonuc);
        textViewYuzdeSonuc = findViewById(R.id.textViewYuzdeSonuc);
        buttonTekrar = findViewById(R.id.buttonTekrar);

        dogruSayac = getIntent().getIntExtra("dogruSayac",0);
        yanlisSonuc = 20 - dogruSayac;
        toplamSoru = dogruSayac + yanlisSonuc;

        textViewSonuc.setText(dogruSayac+" DOĞRU "+ yanlisSonuc +" YANLIŞ");
        textViewYuzdeSonuc.setText("% "+(dogruSayac*100)/toplamSoru+" Başarı");

        SharedPreferences preferences = getSharedPreferences("ToplamSoru",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("toplamSoru",toplamSoru);
        editor.apply();

        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this,QuizActivity.class));
                finish(); // backstack 'ten siliyorum. Bir adım gitmeyip ana sayfaya dönecektir.
            }
        });
    }
}