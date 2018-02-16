package com.example.a49876.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView theDate;
    private Button btnGoCalendar;
    private Button btnGoAllJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        // Unused button at the moment
        this.btnGoAllJournal = findViewById(R.id.btnGoAllJournal);

        // Display the calendar activity
        this.btnGoCalendar = findViewById(R.id.btnGoCalendar);
        this.btnGoCalendar.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view)
           {
               Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
               startActivity(intent);
           }
        });
<<<<<<< HEAD
        btnGoAllJournal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, allJournalActivity.class);
                startActivity(intent);
            }
        });
=======
>>>>>>> a3497dd4235196e34c3d6f9823680274d01f1fef
    }
}