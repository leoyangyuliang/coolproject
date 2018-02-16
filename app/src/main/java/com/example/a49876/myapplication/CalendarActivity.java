package com.example.a49876.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity{
    private static final String TAG = "CalendarActivity";

    private CalendarView calendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.calendar_layout);

        // Display the options activity
        this.calendarView = findViewById(R.id.calendarView);
        this.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int y, int m, int d){
                String date = (m + 1) + "-" + d + "-" + y;
                Intent intent = new Intent(CalendarActivity.this, Options.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}
