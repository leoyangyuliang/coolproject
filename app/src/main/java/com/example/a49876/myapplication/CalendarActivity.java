package com.example.a49876.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

/**
 * Manage the calendar layout, where user can choose a date and view its options page
 */
public class CalendarActivity extends AppCompatActivity{
    private static final String TAG = "CalendarActivity";

    // Create a calendar
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Calendar");
        setContentView(R.layout.calendar_layout);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        // View the option page
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int y, int m, int d){
                String date = (m+1) + "-" + d + "-" + y;
                Intent intent = new Intent(CalendarActivity.this, JournalActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }
}
