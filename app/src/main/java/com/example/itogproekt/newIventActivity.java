package com.example.itogproekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;

import static com.example.itogproekt.MainActivity.myCalendar;

public class newIventActivity extends AppCompatActivity {
    EditText themeIventEDT, dateIventEDT, startTimeIventETD, finishTimeIventEDT;
    Button backIventBTN, createNewIventBTN;
    String willReformed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ivent);
        themeIventEDT = findViewById(R.id.themeIventEDT);
        dateIventEDT = findViewById(R.id.dateIventEDT);
        startTimeIventETD = findViewById(R.id.timeStartIventEDT);
        finishTimeIventEDT = findViewById(R.id.timeFinishIventEDT);
        backIventBTN = findViewById(R.id.backIventBTN);
        createNewIventBTN = findViewById(R.id.createNewIventBTN);


        createNewIventBTN.setOnClickListener(v -> {
            if (!themeIventEDT.equals("") && !dateIventEDT.equals("") && !startTimeIventETD.equals("") && !finishTimeIventEDT.equals("")) {
                willReformed = dateIventEDT.getText().toString();
                char[] middle = willReformed.toCharArray();
                for (int i=0;i<= middle.length;i++){
                    if (middle[i] == '.'){
                        middle[i] = '-';
                    }
                }
                myCalendar.addEvent(middle.toString(),startTimeIventETD.getText().toString(),finishTimeIventEDT.getText().toString(),themeIventEDT.getText().toString());
                Toast.makeText(this, "You are create Event", Toast.LENGTH_SHORT).show();
            }
        });


        backIventBTN.setOnClickListener(v -> {
            finish();
        });
    }
}
