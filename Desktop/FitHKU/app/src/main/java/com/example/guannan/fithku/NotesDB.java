package com.example.guannan.fithku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by guannan on 2/12/2016.
 */

public class NotesDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME="notes";
    public static final String CONTENT="content";
    public static final String ID="_id";//必须是下划线id
    public static final String TIME="time";
    public static final String PATH="path";
    public static final String VIDEO="video";


    public NotesDB (Context context){
        super(context,"notes",null,1);//notes是库名
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT," + CONTENT
        + " TEXT NOT NULL," + PATH
        + " TEXT " + VIDEO
        + " TEXT " + TIME
        + " TEXT NOT NULL)         ");//每个类型前都需要加一个小空格

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){

    }

}
