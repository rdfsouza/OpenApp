package com.example.openapp3.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseProfile extends SQLiteOpenHelper {


    public DataBaseProfile(Context context) {

        super(context, "Data.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text, AccountType text, Gender text, Age text, Name text, AboutMe text,Ocupation text,Education text,Location text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    public boolean insert(String email, String password, String AccountType, String Gender, String Age,String Name, String AboutMe, String Ocupation, String Education, String Location) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("AccountType", AccountType);
        contentValues.put("Gender", Gender);
        contentValues.put("Age", Age);
        contentValues.put("Name", Name);
        contentValues.put("AboutMe", AboutMe);
        contentValues.put("Ocupation", Ocupation);
        contentValues.put("Education", Education);
        contentValues.put("Location", Location);
        long ins = db.insert("user", null, contentValues);
        if (ins == 1) return false;
        else return true;

    }


    public boolean emailPass(String email, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email =? and password=?", new String[] {email, password});
        if (cursor.getCount()>0)return true;
        else return false;

    }

    public Cursor AllData(String email){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email =? ", new String[] {email});

        return cursor;
    }




    public Boolean Update(String email, String Name,String Gender,String Age,String Location,String Ocupation,String Education,String AboutMe){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("Gender", Gender);
        contentValues.put("Age", Age);
        contentValues.put("Name", Name);
        contentValues.put("AboutMe", AboutMe);
        contentValues.put("Ocupation", Ocupation);
        contentValues.put("Education", Education);
        contentValues.put("Location", Location);
        db.update("user" ,contentValues, "email = ?", new String[] {email} );

        return true;


    }



}

