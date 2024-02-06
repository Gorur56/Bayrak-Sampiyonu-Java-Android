package com.firstapp.bayraksampiyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button buttonBasla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBasla = findViewById(R.id.buttonBasla);

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