package com.firstapp.bayraksampiyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button buttonBasla, buttonDevamEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBasla = findViewById(R.id.buttonBasla);
        buttonDevamEt = findViewById(R.id.buttonDevamEt);

        try {
            veritabaniKopyala(); // İlk açıldığında bir kere veritabanını kopyalayacak.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buttonBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,QuizActivity.class));
            }
        });

        buttonDevamEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("OyunDurumu", MODE_PRIVATE);
                int yanlisSayac = preferences.getInt("yanlisSayac", 0); // Varsayılan olarak 1
                int dogruSayac = preferences.getInt("dogruSayac", 0); // Varsayılan olarak 0
                int soruSayac = preferences.getInt("soruSayac",0);
                
                if ( soruSayac == 19 )
                {
                    startActivity(new Intent(MainActivity.this,QuizActivity.class));
                }
                else
                {
                    startGame(yanlisSayac, dogruSayac, soruSayac);
                }
            }
        });
    }

    private void startGame(int yanlisSayac, int dogruSayac, int soruSayac) {
        // Verileri taşımak için bir Intent oluştur
        Intent intent = new Intent(this, QuizActivity.class);

        // Seviye ve puan bilgilerini Intent'e ekleyerek taşı
        intent.putExtra("yanlisSayac", yanlisSayac);
        intent.putExtra("dogruSayac", dogruSayac);
        intent.putExtra("soruSayac", soruSayac);

        // Intent'i kullanarak diğer aktiviteyi başlat
        startActivity(intent);
    }

    public void veritabaniKopyala() throws IOException {
        DatabaseCopyHelper helper = new DatabaseCopyHelper(this);
        try {
            helper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        helper.openDataBase();
    }
}