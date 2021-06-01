package com.example.itogproekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import java.util.Date;

public class NewAlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY,sHour);
        calendar.set(calendar.MINUTE, sMin);
        calendar.set(calendar.SECOND, 0);
        calendar.set(calendar.MILLISECOND, 0);
        long sdl = calendar.getTimeInMillis();

        Intent intent = new Intent(NewAlarmActivity.this, MainActivity.class);
        PendingIntent sender = PendingIntent.getBroadcast(NewAlarmActivity.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager ALARM1 = (AlarmManager)getSystemService(ALARM_SERVICE);
        ALARM1.set(AlarmManager.RTC_WAKEUP, sdl, sender);


        
    }
}