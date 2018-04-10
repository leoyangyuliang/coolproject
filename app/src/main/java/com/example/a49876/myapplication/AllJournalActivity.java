package com.example.a49876.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class AllJournalActivity extends AppCompatActivity {
    private ListView allJournalListView;
    private ArrayList<String> alljournalslist;
    private String[] alljournals;

    private static final String TAG = "AllJournalActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle("All Journals");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_journal_layout);
        FileUtils fileutils = new FileUtils();

        File path = getFilesDir();
        File file = new File(path, "AllJournals.bin");

        //get all journals arraylist
        alljournalslist = (ArrayList) fileutils.readFromBinary(file);

        //convert arraylist to array
        if (!alljournalslist.isEmpty()) {
            Log.e("alljournal(0)", alljournalslist.get(0));
            //change array list to string array
            alljournals = alljournalslist.toArray(new String[alljournalslist.size()]);
            //Log.e("alljournals",alljournals[0]);

            //create list view
            allJournalListView = (ListView) findViewById(R.id.allJournalListView);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.textview, alljournals);
            //CustomAdapter my_adapter = new CustomAdapter(this, alljournalslist);
            //allJournalListView.setAdapter(my_adapter);
            allJournalListView.setAdapter(adapter);
            allJournalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String date = String.valueOf(adapterView.getItemAtPosition(i));
                    Log.e("show date", date);
                    Intent intent = new Intent(AllJournalActivity.this, JournalActivity.class);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }
            });
        } else {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("No Journal")
                    .setMessage("Do you want to write a Journal right now?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(AllJournalActivity.this, CalendarActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    })
                    .show();
        }
    }
}
