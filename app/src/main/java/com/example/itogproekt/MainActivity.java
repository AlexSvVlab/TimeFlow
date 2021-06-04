package com.example.itogproekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.desai.vatsal.mydynamiccalendar.EventModel;
import com.desai.vatsal.mydynamiccalendar.GetEventListListener;
import com.desai.vatsal.mydynamiccalendar.MyDynamicCalendar;
import com.desai.vatsal.mydynamiccalendar.OnDateClickListener;
import com.desai.vatsal.mydynamiccalendar.OnEventClickListener;
import com.desai.vatsal.mydynamiccalendar.OnWeekDayViewClickListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    Button button, button2, button3;
    static MyDynamicCalendar myCalendar;
    public static int UsersId = 0;
    static MyOpenHelper dbHelper;
    static int EventCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button.setOnClickListener(v -> {
            Intent intentToLogIn = new Intent(this, loginActivity.class);
            startActivity(intentToLogIn);
        });
        button2.setOnClickListener(v -> {
            Intent intentToNewIvent = new Intent(this,newIventActivity.class);

            startActivity(intentToNewIvent);
        });
        button3.setOnClickListener(v -> {

        });

        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

//        myCalendar = new MyDynamicCalendar(this);

        //Add MyDynamicRecyclerView
        myCalendar = findViewById(R.id.myCalendar);

        //Design Calendar View
        myCalendar.setCalendarBackgroundColor("#eeeeee"); //or  myCalendar.setCalendarBackgroundColor(R.color.gray);
        myCalendar.setHeaderBackgroundColor("#4169e1");
        myCalendar.setHeaderTextColor("#ffffff");
        myCalendar.setNextPreviousIndicatorColor("#6f7b7d");
        myCalendar.setWeekDayLayoutBackgroundColor("#badbad");
        myCalendar.setWeekDayLayoutTextColor("#ffffff");
        myCalendar.setExtraDatesOfMonthBackgroundColor("#4d7198");
        myCalendar.setExtraDatesOfMonthTextColor("#a7fc00");
        myCalendar.setDatesOfMonthBackgroundColor("#606e8c");
        myCalendar.setDatesOfMonthTextColor("#ffffff");
        myCalendar.setCurrentDateBackgroundColor(R.color.black);
        myCalendar.setCurrentDateTextColor("#ff0000");
        myCalendar.setBelowMonthEventTextColor("#102c54");
        myCalendar.setBelowMonthEventDividerColor("#635478");

        //Manage Saturday & Sunday
        // set all saturday off(Holiday) - default value is false
        // isSaturdayOff(true/false, date_background_color, date_text_color);
        myCalendar.isSaturdayOff(true, "#ffffff", "#ff0000");

        // set all sunday off(Holiday) - default value is false
        // isSundayOff(true/false, date_background_color, date_text_color);
        myCalendar.isSundayOff(true, "#ffffff", "#ff0000");

        //Manage Events
        myCalendar.setEventCellBackgroundColor("#ca2c92");
        myCalendar.setEventCellTextColor("#fffff0");

        // Add event  -  addEvent(event_date, event_start_time, event_end_time, event_title)
        myCalendar.addEvent("24-5-2021", "8:00", "8:15", "Today Event 1");
        myCalendar.addEvent("25-5-2021", "8:15", "8:30", "Today Event 2");
        myCalendar.addEvent("26-5-2021", "8:30", "8:45", "Today Event 3");

        // Get list of event with detail
        myCalendar.getEventList(new GetEventListListener() {
            @Override
            public void eventList(ArrayList<EventModel> eventList) {

            }
        });

        // updateEvent(position, event_date, event_start_time, event_end_time, event_title)
        //myCalendar.updateEvent(0, "5-10-2016", "8:00", "8:15", "Today Event 111111");

        // Delete single event
       // myCalendar.deleteEvent(2);

        // Delete all events
       // myCalendar.deleteAllEvent();

        //Manage Holiday
        myCalendar.setHolidayCellBackgroundColor("#654248");
        myCalendar.setHolidayCellTextColor("#d590bb");

        // set holiday date clickable true/false
        myCalendar.setHolidayCellClickable(false);

        // Add holiday  -  addHoliday(holiday_date)
        myCalendar.addHoliday("2-11-2016");
        myCalendar.addHoliday("13-11-2016");
        myCalendar.addHoliday("8-10-2016");
        myCalendar.addHoliday("10-12-2016");

        //Navigate to particular date
        // setCalendarDate(date, month, year)
        myCalendar.setCalendarDate(day, month, year);
        //month View
        // show month view
        myCalendar.showMonthView();

        // date click listener
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {

            }

            @Override
            public void onLongClick(Date date) {

            }
        });

        //Month View With Event List (Show event on event date click)
        // show month view with events
        myCalendar.showMonthViewWithBelowEvents();

        // date click listener
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {

            }

            @Override
            public void onLongClick(Date date) {

            }
        });

        //Week View
        // show week view
        myCalendar.showWeekView();

        // date click listener
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {

            }

            @Override
            public void onLongClick(Date date) {

            }
        });

        // week view blank cell click listener
        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {

            }

            @Override
            public void onLongClick(String date, String time) {

            }
        });

        // single event cell click listener
        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {

            }

            @Override
            public void onLongClick() {

            }
        });

        //Day View
        // show day view
        myCalendar.showDayView();

        // day view blank cell click listener
        myCalendar.setOnWeekDayViewClickListener(new OnWeekDayViewClickListener() {
            @Override
            public void onClick(String date, String time) {

            }

            @Override
            public void onLongClick(String date, String time) {

            }
        });

        // single event cell click listener
        myCalendar.setOnEventClickListener(new OnEventClickListener() {
            @Override
            public void onClick() {

            }

            @Override
            public void onLongClick() {

            }
        });

        //Agenda View
        // show agenda view
        myCalendar.showAgendaView();

        // date click listener
        myCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onClick(Date date) {

            }

            @Override
            public void onLongClick(Date date) {

            }
        });
    }
    MenuItem monthMenu, agendaMenu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.some_menu, menu);
        monthMenu = menu.getItem(0);
        agendaMenu = menu.getItem(1);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (!item.isChecked())
            item.setChecked(true);
        else
            item.setChecked(false);
        switch (id) {
            case R.id.fn1:
                if (monthMenu.isChecked() && item.isChecked())
                    agendaMenu.setChecked(false);


                myCalendar.setOnDateClickListener(new OnDateClickListener() {
                    @Override
                    public void onClick(Date date) {

                    }

                    @Override
                    public void onLongClick(Date date) {

                    }
                });
                //Month View With Event List (Show event on event date click)
                // show month view with events
                myCalendar.showMonthViewWithBelowEvents();

                // date click listener
                myCalendar.setOnDateClickListener(new OnDateClickListener() {
                    @Override
                    public void onClick(Date date) {

                    }

                    @Override
                    public void onLongClick(Date date) {

                    }
                });
                Toast.makeText(MainActivity.this, "Month", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fn2:
                if (agendaMenu.isChecked()&& item.isChecked()){
                    monthMenu.setChecked(false);
                    // Get list of event with detail
                    myCalendar.setOnDateClickListener(new OnDateClickListener() {
                        @Override
                        public void onClick(Date date) {

                        }

                        @Override
                        public void onLongClick(Date date) {

                        }
                    });
                    myCalendar.getEventList(new GetEventListListener() {
                        @Override
                        public void eventList(ArrayList<EventModel> eventList) {

                        }
                    });
                    myCalendar.showAgendaView();
                    // date click listener
                    myCalendar.setOnDateClickListener(new OnDateClickListener() {
                        @Override
                        public void onClick(Date date) {

                        }

                        @Override
                        public void onLongClick(Date date) {

                        }
                    });

                Toast.makeText(MainActivity.this, "Agenda", Toast.LENGTH_SHORT).show();}
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}