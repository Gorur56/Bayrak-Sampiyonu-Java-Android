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
    private int soruSayac = 0;
    private int yanlisSayac = 0;
    private int dogruSayac = 0;


    //Soru seçeneklerini karıştırmak
    private HashSet<Bayraklar> seceneklerKaristirmaListe = new HashSet<>();
    //HAshset 'te index numarası alamdığımız için Arrayliste akrataracağız.
    private ArrayList<Bayraklar> seceneklerListesi = new ArrayList<>();

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

        soruYukle();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonA); //İlk önce doğru kontrolü yapıyoruz
                sayacKontrol();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonB);
                sayacKontrol();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonC);
                sayacKontrol();
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonD);
                sayacKontrol();
            }
        });
    }

    public void soruYukle(){
        textViewSoruSayi.setText((soruSayac+1)+".SORU"); //Soru değiştikçe soru sayısı artar.

        //Doğru sayacı ve Yanlış sayacı ayarla
        textViewDogru.setText("Doğru: "+dogruSayac);
        textViewYanlis.setText("Yanlış: "+yanlisSayac);

        dogruSoru = sorularListe.get(soruSayac);

        //Doğrulanmış seçenekleri ayıklıyoruz.
        yanlisSeceneklerListe = new BayraklarDao().rasgele3YanlisSecenekGetir(vt,dogruSoru.getBayrak_id());

        //Bayrağı değiştirelim. R.id üzerinden değil dinamik bir şekilde erişiyoruz.
        imageViewBayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(), "drawable", getPackageName()));

        //Seçenekleri oluşturmadan temizleyelim.
        seceneklerKaristirmaListe.clear();

        //Önce doğru soruyu ekleyelim.
        seceneklerKaristirmaListe.add(dogruSoru);
        seceneklerKaristirmaListe.add(yanlisSeceneklerListe.get(0));
        seceneklerKaristirmaListe.add(yanlisSeceneklerListe.get(1));
        seceneklerKaristirmaListe.add(yanlisSeceneklerListe.get(2));

        seceneklerListesi.clear();

        for (Bayraklar b:seceneklerKaristirmaListe) {
            seceneklerListesi.add(b);
        }

        //Karıştırılan seçeneler tek tek eklenir
        buttonA.setText(seceneklerListesi.get(0).getBayrak_ad());
        buttonB.setText(seceneklerListesi.get(1).getBayrak_ad());
        buttonC.setText(seceneklerListesi.get(2).getBayrak_ad());
        buttonD.setText(seceneklerListesi.get(3).getBayrak_ad());

    }

    public void dogruKontrol(Button button){
        String buttonYazi = button.getText().toString();
        String dogruCevap = dogruSoru.getBayrak_ad();

        if(buttonYazi.equals(dogruCevap))
        {
            dogruSayac++;
            //Doğru cevap verildi
        }
        else
        {
            yanlisSayac++;
            //Yanlış cevap verildi.
        }

        textViewDogru.setText("Doğru: "+dogruSayac);
        textViewYanlis.setText("Yanlış: "+yanlisSayac);
    }

    public void sayacKontrol()
    {
        soruSayac++;

        if (soruSayac != 20 )
        {
            soruYukle();
        }
        else
        {
            //Öbür tarafa doğru sayacı göndereceğiz.
            Intent intent = new Intent( QuizActivity.this,ResultActivity.class);
            intent.putExtra("dogruSayac",dogruSayac);
            startActivity(intent);
            finish(); // backstack 'ten siliyorum. Bir adım gitmeyip ana sayfaya dönecektir.
        }
    }
}