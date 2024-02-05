package com.firstapp.bayraksampiyonu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(Context context) {
        super(context, "bayrakquiz.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // IF NOT EXISTS => Eğer yoksa
        //Tablo oluştur
        db.execSQL("CREATE TABLE IF NOT EXISTS 'bayraklar'(\n" +
                "\t'bayrak_id' INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t'bayrak_ad' TEXT,\n" +
                "\t'bayrak_resim' TEXT\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bayraklar"); //varsa sil
        onCreate(db); //yerleştir.
    }
}
