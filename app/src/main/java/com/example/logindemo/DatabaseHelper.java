package com.example.logindemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String COL2="username";
    public static final String COL3="password";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user( username text primary key , password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user ");
    }

    //insert
    public boolean insert(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long ins=db.insert("user",null,contentValues);
        if(ins==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from user where username=? and password=?",new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }



    public boolean checkUserAndPwd(String username,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from user where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }


    }


}
