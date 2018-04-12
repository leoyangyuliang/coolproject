package com.example.a49876.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Manage work list for the selected date
 */
public class WorklistActivity extends AppCompatActivity implements Serializable {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Worklist");
        setContentView(R.layout.worklist_layout);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("hello");
        strings.add("world");
        strings.add("!");
        final WorklistAdapter adapter = new WorklistAdapter(this, strings);
        ListView listView = findViewById(R.id.worklistListView);
        listView.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("to-do");
            }
        });
    }
}
