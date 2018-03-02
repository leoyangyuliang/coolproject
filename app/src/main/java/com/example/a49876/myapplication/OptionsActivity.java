package com.example.a49876.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Displays a list of features for a date
 */
public class OptionsActivity extends AppCompatActivity {
    private static final String TAG = "OptionsActivity";
    private Button btnGoJournal;
    private Button btnGoEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);

        // Create an intent, and store the selected date
        Intent incomingIntent = getIntent();
        final String date = incomingIntent.getStringExtra("date");
        setTitle(date);

        // View the journal page
        btnGoJournal = (Button) findViewById(R.id.goJournalFromChoicePage);
        btnGoJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionsActivity.this, JournalActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        // View the events page
        btnGoEvents = (Button) findViewById(R.id.goEventsFromChoicePage);
        btnGoEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionsActivity.this, EventsActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}
