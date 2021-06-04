package com.example.itogproekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.view.View;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;

import static com.example.itogproekt.MainActivity.EventCount;
import static com.example.itogproekt.MainActivity.myCalendar;

public class newIventActivity extends AppCompatActivity {
    EditText themeIventEDT, dateIventEDT, startTimeIventETD, finishTimeIventEDT;
    Button backIventBTN, createNewIventBTN;
    String willReformed;
    SQLiteDatabase db;
    static MyOpenHelper dbHelper;
    private static final int NOTIFY_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new MyOpenHelper(this, "db",null,1);
        db = dbHelper.getReadableDatabase();
        db = dbHelper.getWritableDatabase();
        String CHANNEL_ID = "Уведомления";


        setContentView(R.layout.activity_new_ivent);
        themeIventEDT = findViewById(R.id.themeIventEDT);
        dateIventEDT = findViewById(R.id.dateIventEDT);
        startTimeIventETD = findViewById(R.id.timeStartIventEDT);
        finishTimeIventEDT = findViewById(R.id.timeFinishIventEDT);
        backIventBTN = findViewById(R.id.backIventBTN);
        createNewIventBTN = findViewById(R.id.createNewIventBTN);


        createNewIventBTN.setOnClickListener(v -> {
            if (MainActivity.EventCount == 0) {
                if (!themeIventEDT.equals("") && !dateIventEDT.equals("") && !startTimeIventETD.equals("") && !finishTimeIventEDT.equals("")) {
                    myCalendar.addEvent(dateIventEDT.getText().toString(), startTimeIventETD.getText().toString(), finishTimeIventEDT.getText().toString(), themeIventEDT.getText().toString());
                    if (MainActivity.UsersId != 0) {
                        String qEventPlus = "INSERT INTO event(userId, date, startTime, finishTime, theme) VALUES (?,?,?,?,?)";
                        db.rawQuery(qEventPlus, new String[]{String.valueOf(MainActivity.UsersId), dateIventEDT.getText().toString(), startTimeIventETD.getText().toString(),
                                finishTimeIventEDT.getText().toString(), themeIventEDT.getText().toString()});
                        Toast.makeText(this, "You are create Event", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "You are create Event, please log in", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                    EventCount++;
                }
            } else {
                myCalendar.addEvent(dateIventEDT.getText().toString(), startTimeIventETD.getText().toString(), finishTimeIventEDT.getText().toString(), themeIventEDT.getText().toString());
                Toast.makeText(this,"Event create", Toast.LENGTH_SHORT);
                finish();
            }
        });


        backIventBTN.setOnClickListener(v -> {
            finish();
        });
    }
}
