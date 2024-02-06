package com.firstapp.bayraksampiyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewDogru, textViewYanlis, textViewSoruSayi;
    private ImageView imageViewBayrak;
    private Button buttonA, buttonB, buttonC, buttonD;
    private ArrayList<Bayraklar> sorularListe;
    private ArrayList<Bayraklar> yanlisSeceneklerListe;
    private Bayraklar dogruSoru;
    private Veritabani vt;

    //Sayaçlar
    private int soruSayaç = 0;
    private int yanlisSayac = 0;
    private int dogruSayac = 0;


    //Soru seçeneklerini karıştırmak
    private HashSet<Bayraklar> seceneklerKaristirmaListe = new HashSet<>();
    //HAshset 'te index numarası alamdığımız için Arrayliste akrataracağız.
    private ArrayList<Bayraklar> secenekler = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruSayi = findViewById(R.id.textViewSoruSayi);

        imageViewBayrak = findViewById(R.id.imageViewBayrak);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        //Veritabanı işlemleri oluşturmak için vt yioluşturduk.
        vt = new Veritabani(this);
        sorularListe = new BayraklarDao().rasgele20Getir(vt); //Soru listemiz yüklensin.

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( QuizActivity.this,ResultActivity.class));
                finish(); // backstack 'ten siliyorum. Bir adım gitmeyip ana sayfaya dönecektir.
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}