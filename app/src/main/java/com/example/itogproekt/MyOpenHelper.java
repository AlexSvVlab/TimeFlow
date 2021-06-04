package com.example.itogproekt;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public  class MyOpenHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public MyOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        this.db = db;

        String qUser = "CREATE TABLE user\n"+
                "\t(id INTEGER PRIMARY KEY AUTOINCREMENT,\n"+
                "      email TEXT NOT NULL UNIQUE,\n" +
                "      pass TEXT NOT NULL )";
        db.execSQL(qUser);
        String  qEvent = "CREATE TABLE event\n"+
                "   (id INTEGER PRIMARY KEY AUTOINCREMENT,\n "+
                "   userId INTEGER NOT NULL,\n" +
                "   date TEXT NOT NULL,\n" +
                "   startTime TEXT NOT NULL,\n" +
                "   finishTime TEXT NOT NULL,\n" +
                "   theme TEXT NOT NULL,\n" +
                "   FOREIGN KEY (userId) REFERENCES user(id))";
        db.execSQL(qEvent);
        db.execSQL("INSERT INTO user(email,pass) VALUES ('admin','admin')");
        db.execSQL("INSERT INTO user(email, pass) VALUES ('test','test')");
        db.execSQL("INSERT INTO event(userId, date, startTime, finishTime, theme) VALUES(1,'18.10.2021','18:00','23:00','ADMIN_HAPPY_BIRTHSDAY')");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ){

    }
}
