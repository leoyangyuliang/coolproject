package com.example.a49876.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class AllJournalActivity extends AppCompatActivity {
    private ListView allJournalListView;
    private String[] myary= {"a","b","c"};

    private static final String TAG = "AllJournalActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle("All Journals");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_journal_layout);

        allJournalListView = (ListView) findViewById(R.id.allJournalListView);



        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.textview, myary);
        allJournalListView.setAdapter(adapter);
//        File file = getFilesDir();
//        File path = new File(file, "alljournal.bin");
//        WriteObject writeobject = new WriteObject();
//        writeobject.writeToBinary(myary, getFilesDir(), "alljournal.bin");
//        ReadObject readobject = new ReadObject();
//        ary =  (String[]) readobject.readFromBinary(path);
//        Log.e("testing", ary[0]);
    }
}
