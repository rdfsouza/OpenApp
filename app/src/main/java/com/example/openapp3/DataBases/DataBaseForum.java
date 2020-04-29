package com.example.openapp3.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseForum extends SQLiteOpenHelper {


    public DataBaseForum(Context context) {

        super(context, "Forum.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Forum(ID integer primary key AUTOINCREMENT, NameUs text, TimeUs text, ChatUs text, Type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Forum");
    }

    public boolean insert(String NameUs,String TimeUs,String ChatUs,String Type) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameUs", NameUs);
        contentValues.put("TimeUs", TimeUs);
        contentValues.put("ChatUs", ChatUs);
        contentValues.put("Type", Type);



        long ins = db.insert("Forum", null, contentValues);
        if (ins == 1) return false;
        else return true;

    }


    public Cursor AllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Forum group by type", new String[] {});

        return cursor;
    }
    public Cursor AllTopic(String Name){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Forum where Type=? ", new String[] {Name});

        return cursor;
    }
    public Cursor AllMessages(String Name,String eHelper){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Forum where NameUs=? and Namehelp=? or NameHelp=? and NameUs=? ", new String[] {Name,eHelper,Name,eHelper});

        return cursor;
    }


}