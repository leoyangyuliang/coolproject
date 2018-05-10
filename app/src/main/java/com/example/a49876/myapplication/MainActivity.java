package com.example.a49876.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

/**
 * The page displayed when first opening the application
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnGoCalendar;
    private Button btnGoAllJournal;
    private Button btnGoWorklist;
    private Button btnGraph;
    private Button btnGoPublicJournals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        // View the calender page
        btnGoCalendar = findViewById(R.id.btnGoCalendar);
        btnGoCalendar.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view)
           {
               Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
               startActivity(intent);
           }
        });


        // View the all journals page
        btnGoAllJournal = findViewById(R.id.btnGoAllJournal);
        btnGoAllJournal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                FileUtils fileutils = new FileUtils();
                File path = getFilesDir();
                File file = new File(path, "AllJournals.bin");
                ArrayList<String> alljournalslist = (ArrayList) fileutils.readFromBinary(file);
                if(alljournalslist.isEmpty()){
                    AlertDialog.Builder builder;

                    builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("No Journal")
                            .setMessage("Do you want to start your first Journal?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })

                            .show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, AllJournalActivity.class);
                    startActivity(intent);
                }
            }
        });

        // View the work list page
        btnGoWorklist = findViewById(R.id.btnGoWorklist);
        btnGoWorklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorklistActivity.class);
                startActivity(intent);
            }
        });

        // View the example graph
        btnGraph = findViewById(R.id.btnGoGraph);
        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });


        //view the stories
        btnGoPublicJournals = findViewById(R.id.btnGoPublicJournals);
        btnGoPublicJournals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StoriesActivity.class);
                startActivity(intent);
            }
        });
    }
}