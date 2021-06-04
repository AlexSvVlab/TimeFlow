package com.example.itogproekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class registrationActivity extends AppCompatActivity {
    EditText mailRegETD, passRegETD , repeatRegETD;
    Button backRegBTN, createACCBTN;
    SQLiteDatabase db;
    static MyOpenHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new MyOpenHelper(this, "db",null,1);
        db = dbHelper.getReadableDatabase();
        db = dbHelper.getWritableDatabase();

        setContentView(R.layout.activity_registration);
        mailRegETD = findViewById(R.id.mailRegistrationEDT);
        passRegETD = findViewById(R.id.passwordRegistrationEDT);
        repeatRegETD = findViewById(R.id.repeatRegistrationEDT);
        backRegBTN= findViewById(R.id.backRegBTN);
        createACCBTN = findViewById(R.id.createAccRegBTN);

        backRegBTN.setBackgroundColor(Color.BLUE);
        createACCBTN.setBackgroundColor(Color.BLUE);

        backRegBTN.setOnClickListener(v -> {
            Intent intentToLogIn = new Intent(registrationActivity.this, loginActivity.class);
            startActivity(intentToLogIn);
        });
        createACCBTN.setOnClickListener(v -> {
            if (!mailRegETD.getText().equals("") & !passRegETD.getText().equals("") & !repeatRegETD.getText().equals("")) {
                if(passRegETD.getText()==repeatRegETD.getText()){
                    //Вписать добавление нового пользователя или в бд или в фаер
                    ContentValues newUser = new ContentValues();
                    newUser.put("email", mailRegETD.getText().toString());
                    newUser.put("pass", passRegETD.getText().toString());
                    db.insert("user",null,newUser);
                    Toast.makeText(this, "You are registered",Toast.LENGTH_SHORT);


            }}
            finish();
        });
    }
}