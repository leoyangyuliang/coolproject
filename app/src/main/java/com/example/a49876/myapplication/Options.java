package com.example.a49876.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {
    private static final String TAG = "Options";
    private Button viewJournal;
    private Button viewEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.options_layout);

        Intent incomingIntent = getIntent();
        final String date = incomingIntent.getStringExtra("date");
        super.setTitle(date);

        // Display the journal activity
        this.viewJournal = findViewById(R.id.goJournalFromChoicePage);
        this.viewJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Options.this, JournalActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        // Display the event activity
        this.viewEvents = findViewById(R.id.goEventsFromChoicePage);
        this.viewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, EventActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}
