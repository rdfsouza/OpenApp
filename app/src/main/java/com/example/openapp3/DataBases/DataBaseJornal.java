package com.example.openapp3.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseJornal extends SQLiteOpenHelper {


    public DataBaseJornal(Context context) {

        super(context, "Jornals.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Jornals(email text primary key, paperJornal text, date text, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Jornals");
    }

    public boolean insert(String email, String paperJornal, String date,String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("paperJornal", paperJornal);
        contentValues.put("date", date);
        contentValues.put("name", name);

        long ins = db.insert("Jornals", null, contentValues);
        if (ins == 1) return false;
        else return true;

    }


    public Cursor AllData(String email){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Jornals where email =? ", new String[] {email});

        return cursor;
    }

    public Boolean Update(String email, String paperJornal, String date,String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("paperJornal", paperJornal);
        contentValues.put("date", date);
        contentValues.put("name", name);
        db.update("Jornals" ,contentValues, "email = ?", new String[] {email} );

        return true;


    }

}
