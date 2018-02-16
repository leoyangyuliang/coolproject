package com.example.a49876.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView theDate;
    private Button btnGoCalendar;
    private Button btnGoAllJournal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);
        btnGoAllJournal = (Button) findViewById(R.id.btnGoAllJournal);


        btnGoCalendar.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view)
           {
               Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
               startActivity(intent);
           }
        });

    }
}