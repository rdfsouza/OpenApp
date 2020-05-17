package com.example.openapp3.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, "Helpers.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Helpers(email text primary key, password text, Gender text, Age text, Location text, Name text ,AboutMe text, Type text,Education text, Occupation text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Helpers");
    }
    public boolean insert(String email, String password, String Gender,String Age, String Location, String Name, String AboutMe,String Type,String Education,String Occupation) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("Gender", Gender);
        contentValues.put("Age", Age);
        contentValues.put("Location", Location);
        contentValues.put("Name", Name);
        contentValues.put("AboutMe", AboutMe);
        contentValues.put("Type", Type);
        contentValues.put("Education", Education);
        contentValues.put("Occupation", Occupation);

        long ins = db.insert("Helpers", null, contentValues);
        if (ins == 1) return false;
        else return true;

    }
    public boolean emailPass(String email, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Helpers where email =? and password=?", new String[] {email, password});
        if (cursor.getCount()>0)return true;
        else return false;

    }
    public Cursor AllData(String Gender,String Age,String Location){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Helpers where Gender =? ", new String[] {Gender});

        return cursor;
    }
    public Cursor AllData2(String email){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Helpers where email =? ", new String[] {email});

        return cursor;
    }


    public Boolean Update(String email,String Gender,String Age, String Location, String Name, String AboutMe, String Education, String Occupation){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("Gender", Gender);
        contentValues.put("Age", Age);
        contentValues.put("Location", Location);
        contentValues.put("Name", Name);
        contentValues.put("AboutMe", AboutMe);
        contentValues.put("Education", Education);
        contentValues.put("Occupation", Occupation);
        db.update("Helpers" ,contentValues, "email = ?", new String[] {email} );

        return true;


    }



}
