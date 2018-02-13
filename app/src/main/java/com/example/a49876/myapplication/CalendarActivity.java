package com.example.a49876.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

/**
 * Created by 49876 on 2/6/2018.
 * this class manage calendar layout, user chose date and go to option page
 */


public class CalendarActivity extends AppCompatActivity{

    private static final String TAG = "CalendarActivity";

    //create variable for calendar
    private CalendarView mCalendarView;

    //activity start
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        //this function manage the onclick event, and go to Option page
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int y, int m, int d){
                String date = (m+1) + "-" + d + "-" + y;
                Intent intent = new Intent(CalendarActivity.this, Options.class);
                //stores date into intent
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }
}
