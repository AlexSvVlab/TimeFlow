package com.example.itogproekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {
    EditText mailLoginEDT, passLoginEDT;
    Button loginLoginBTN, regLoginBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mailLoginEDT = findViewById(R.id.mailLoginEDT);
        passLoginEDT = findViewById(R.id.passwordRegistrationEDT);
        loginLoginBTN = findViewById(R.id.loginLoginBTN);
        regLoginBTN= findViewById(R.id.registrationLoginBTN);

        loginLoginBTN.setBackgroundColor(Color.BLUE);
        regLoginBTN.setBackgroundColor(Color.BLUE);


        regLoginBTN.setOnClickListener(v -> {

            Intent intentToRegistration = new Intent(loginActivity.this, registrationActivity.class);
            startActivity(intentToRegistration);

        });
        loginLoginBTN.setOnClickListener(v -> {

            //Вписать вход в Акк
            finish();

        });
    }
}