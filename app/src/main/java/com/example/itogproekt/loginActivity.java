package com.example.itogproekt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    EditText mailLoginEDT, passLoginEDT;
    Button loginLoginBTN, regLoginBTN;
    boolean passIs, mailIs = false;
    static MyOpenHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new MyOpenHelper(this, "db",null,1);
        db = dbHelper.getReadableDatabase();
        db = dbHelper.getWritableDatabase();

        mailLoginEDT = findViewById(R.id.mailLoginEDT);
        passLoginEDT = findViewById(R.id.passwordLoginEDT);

        loginLoginBTN = findViewById(R.id.loginLoginBTN);
        regLoginBTN= findViewById(R.id.registrationLoginBTN);

        loginLoginBTN.setBackgroundColor(Color.BLUE);
        regLoginBTN.setBackgroundColor(Color.BLUE);


        regLoginBTN.setOnClickListener(v -> {

            Intent intentToRegistration = new Intent(loginActivity.this, registrationActivity.class);
            startActivity(intentToRegistration);


        });
        loginLoginBTN.setOnClickListener(v -> {
            String passString = passLoginEDT.getText().toString();
            String mailString = mailLoginEDT.getText().toString();
            String abc = "SELECT * FROM user";
            //Вписать вход в Акк
            @SuppressLint("Recycle") Cursor cursor1 = db.rawQuery(abc,new String[]{});
            if(cursor1.getCount() > 0){
                cursor1.moveToFirst();
                for (int i = 1;i <= cursor1.getCount(); i++){
                    if(passString == cursor1.getString(cursor1.getColumnIndex("pass")) && mailString == cursor1.getString(cursor1.getColumnIndex("email"))){
                        passIs = true;
                        mailIs = true;
                        break;
                    }
                    cursor1.moveToNext();
                }
            }
            if (passIs && mailIs){
                MainActivity.UsersId = cursor1.getInt(cursor1.getColumnIndex("id"));
            }
            if (MainActivity.UsersId != 0){
                int idUsers = 0;
                idUsers = MainActivity.UsersId;
                String stringIdUsers = String.valueOf(idUsers);
                Cursor cursor2 = db.rawQuery("SELECT * FROM event WHERE userId = ?",new String[]{stringIdUsers});
                if (cursor2.getCount() >0){
                    MainActivity.myCalendar.deleteAllEvent();
                    for (int i = 1; i <= cursor2.getCount();i++){
                        MainActivity.myCalendar.addEvent(cursor2.getString(cursor2.getColumnIndex("date")),cursor2.getString(cursor2.getColumnIndex("startTime")),
                                cursor2.getString(cursor2.getColumnIndex("finishTime")),cursor2.getString(cursor2.getColumnIndex("theme")));
                        cursor2.moveToNext();
                    }

                }

            }
            MainActivity.myCalendar.deleteAllEvent();
            MainActivity.myCalendar.addEvent("18-10-2021","18:00", "23:00","ADMIN_HAPPY_BIRTHDAY");


            Toast.makeText(loginActivity.this, "Successful registration", Toast.LENGTH_SHORT);
            finish();

        });
    }
}