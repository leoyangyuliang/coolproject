package com.example.a49876.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
        alljournalslist = (ArrayList)fileutils.readFromBinary(file);

        //convert arraylist to array
        if(!alljournalslist.isEmpty()) {
            Log.e("alljournal(0)",alljournalslist.get(0));

            alljournals = alljournalslist.toArray(new String[alljournalslist.size()]);
        }
        //create list view
        allJournalListView = (ListView) findViewById(R.id.allJournalListView);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.textview, alljournals);
        allJournalListView.setAdapter(adapter);
        allJournalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }



}
