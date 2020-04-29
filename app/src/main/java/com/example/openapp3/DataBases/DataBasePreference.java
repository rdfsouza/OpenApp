package com.example.openapp3.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBasePreference extends SQLiteOpenHelper {


    public DataBasePreference(Context context) {

        super(context, "Preferences.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Preferences(email text primary key,Name text, Gender text, Age text, Location text, Interest text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Preferences");
}

    public boolean insert(String email,String Name, String Gender,String Age,String Location,String Interest) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("Name", Name);
        contentValues.put("Gender", Gender);
        contentValues.put("Age", Age);
        contentValues.put("Location", Location);
        contentValues.put("Interest", Interest);
        long ins = db.insert("Preferences", null, contentValues);
        if (ins == 1) return false;
        else return true;

    }


    public Boolean Update(String email,String Name, String Gender,String Age,String Location,String Interest){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("Name", Name);
        contentValues.put("Gender", Gender);
        contentValues.put("Age", Age);
        contentValues.put("Location", Location);
        contentValues.put("Interest", Interest);
        db.update("Preferences" ,contentValues, "email = ?", new String[] {email} );

        return true;


    }
    public Cursor AllData(String email){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Preferences where email =? ", new String[] {email});

        return cursor;
    }

}
