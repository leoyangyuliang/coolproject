package com.example.a49876.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Manage events for the selected date
 */
public class EventsActivity extends AppCompatActivity {
    private Button btnGoMain;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_layout);
        btnGoMain = (Button) findViewById(R.id.buttonGoMain);

        // Retrieve the date from incoming intent
        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        setTitle("Events for " + date);

        // View the main page
        btnGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
