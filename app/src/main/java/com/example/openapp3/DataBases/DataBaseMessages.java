package com.example.openapp3.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseMessages extends SQLiteOpenHelper {


    public DataBaseMessages(Context context) {

        super(context, "Chats.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Chats(ID integer primary key AUTOINCREMENT, NameUs text, TimeUs text, NameHelp, TimeHelp text,ChatUs text,ChatHelp text, Type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Chats");
    }

    public boolean insert(String NameUs,String TimeUs,String NameHelp,String TimeHelp, String ChatUs,String ChatHelp,String Type) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameUs", NameUs);
        contentValues.put("TimeUs", TimeUs);
        contentValues.put("NameHelp", NameHelp);
        contentValues.put("TimeHelp", TimeHelp);
        contentValues.put("ChatUs", ChatUs);
        contentValues.put("ChatHelp", ChatHelp);
        contentValues.put("Type", Type);



        long ins = db.insert("Chats", null, contentValues);
        if (ins == 1) return false;
        else return true;

    }




    public Cursor AllData(String Name,String NameHelp){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Chats where NameUs =? or NameHelp=? ", new String[] {Name,NameHelp});

        return cursor;
    }
    public Cursor AllHelper(String Name){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Chats where NameUs=? group by NameHelp", new String[] {Name});

        return cursor;
    }
    public Cursor AllMessages(String Name,String eHelper){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Chats where NameUs=? and Namehelp=? or NameHelp=? and NameUs=? ", new String[] {Name,eHelper,Name,eHelper});

        return cursor;
    }
    public Cursor AllHelper2(String Name){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Chats where NameHelp=? group by NameHelp", new String[] {Name});

        return cursor;
    }

}