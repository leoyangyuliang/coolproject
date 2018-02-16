package com.example.a49876.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 49876 on 2/8/2018.
 */

public class Options extends AppCompatActivity {
    private static final String TAG = "Options";
    private Button goJournalFromOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);
        goJournalFromOption = (Button) findViewById(R.id.goJournalFromChoicePage);
        final Intent intent = new Intent(Options.this, JournalActivity.class);
        Intent incomingIntent = getIntent();
        final String date = incomingIntent.getStringExtra("date");
        setTitle(date);
        goJournalFromOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });

    }
}
