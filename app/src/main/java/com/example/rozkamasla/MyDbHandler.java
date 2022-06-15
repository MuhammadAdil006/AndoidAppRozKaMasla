package com.example.rozkamasla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.PrecomputedText;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler( Context context) {
        super(context, params.DB_NAME,null,params.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE "+params.TABLE_NAME +"("+params.KEY_ID+" INTEGER PRIMARY KEY ,"+params.KEY_NAME+" TEXT )";
        String create1="CREATE TABLE "+ "GOSHT"+"("+params.KEY_ID+" INTEGER PRIMARY KEY ,"+params.KEY_NAME+" TEXT )";
        String create2="CREATE TABLE "+"DAAL" +"("+params.KEY_ID+" INTEGER PRIMARY KEY ,"+params.KEY_NAME+" TEXT )";
        String create3="CREATE TABLE "+"CHAWAL"+"("+params.KEY_ID+" INTEGER PRIMARY KEY ,"+params.KEY_NAME+" TEXT )";
        Log.d("db querry",create);
        db.execSQL(create);
        db.execSQL(create1);
        db.execSQL(create2);
        db.execSQL(create3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addSabzi(String s)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(params.KEY_NAME,s);
        db.insert(params.TABLE_NAME,null,values);
        db.close();
        Log.d("entry","entered successfully");
    }
    public void addGosht(String e)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(params.KEY_NAME,e);
        db.insert("GOSHT",null,values);
        db.close();
        Log.d("entry","entered gosht successfully");
    }
    public void addDaal(String e)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(params.KEY_NAME,e);
        db.insert("DAAL",null,values);
        db.close();
        Log.d("entry","entered daal successfully");
    }
    public void addChawal(String e)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(params.KEY_NAME,e);
        db.insert("CHAWAL",null,values);
        db.close();
        Log.d("entry","entered CHAWAL successfully");
    }
    public ArrayList<String> getAllList(String Tablename)
    {

        SQLiteDatabase db= this.getReadableDatabase();
       ArrayList<String> s= new ArrayList<String>();
        String select= "Select * from "+Tablename;
        Cursor c= db.rawQuery(select,null);
        if(c.moveToFirst())
        {
            do{
                String n= c.getString(1);
                Log.d("list",n);
                s.add(n);
            }while(c.moveToNext());

        }

        return s;
    }
    public boolean deleteRow(String name,String table)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        return db.delete(table, params.KEY_NAME + "='" + name +"' ;", null) > 0;
    }
}
