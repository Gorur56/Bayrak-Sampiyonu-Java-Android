package com.firstapp.bayraksampiyonu;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BayraklarDao {
    public ArrayList<Bayraklar> rasgele20Getir(Veritabani vt){
        ArrayList<Bayraklar> bayraklarArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        //Cursor bayraklar tablosunda rasgele 20 tane veri getirecek.
        Cursor c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 20", null);

        while (c.moveToNext())
        {
            //20 tane rastgele bayrak dönecek.
            Bayraklar b = new Bayraklar(c.getInt(c.getColumnIndex("bayrak_id"))
                                       ,c.getString(c.getColumnIndex("bayrak_ad"))
                                       ,c.getString(c.getColumnIndex("bayrak_resim")));

            bayraklarArrayList.add(b); //listeye ekleniyor.
        }

        return bayraklarArrayList; //liste dönüyor.
    }

    public ArrayList<Bayraklar> rasgele3YanlisSecenekGetir(Veritabani vt, int bayrak_id){

        //bayrad_id doğru seçeneği simgeliyor. Aynıysa cevapla doğru: bayrak_id != "+bayrak_id+"
        ArrayList<Bayraklar> bayraklarArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        //Cursor doğru cevap haricinde 3 tane bayrak getirecek
        Cursor c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != "+bayrak_id+" ORDER BY RANDOM() LIMIT 3", null);

        while (c.moveToNext())
        {
            //20 tane rastgele bayrak dönecek.
            Bayraklar b = new Bayraklar(c.getInt(c.getColumnIndex("bayrak_id"))
                    ,c.getString(c.getColumnIndex("bayrak_ad"))
                    ,c.getString(c.getColumnIndex("bayrak_resim")));

            bayraklarArrayList.add(b); //listeye ekleniyor.
        }

        return bayraklarArrayList; //liste dönüyor.
    }
}
