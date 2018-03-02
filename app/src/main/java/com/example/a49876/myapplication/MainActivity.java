package com.example.a49876.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The page displayed when first opening the application
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnGoCalendar;
    private Button btnGoAllJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View the calender page
        btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);
        btnGoCalendar.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view)
           {
               Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
               startActivity(intent);
           }
        });

        // View the all journals page
        btnGoAllJournal = (Button) findViewById(R.id.btnGoAllJournal);
        btnGoAllJournal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AllJournalActivity.class);
                startActivity(intent);
            }
        });
    }
}