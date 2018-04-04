package com.example.a49876.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Manage events for the selected date
 */
public class WorklistActivity extends AppCompatActivity implements Serializable {
    private Button btnSave;
    private Button btnAdd;
    private Button btnRemove;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Worklist");
        setContentView(R.layout.worklist_layout);

        ArrayList<String> worklist = new ArrayList<String>();
        worklist.add("to-do i guess");
        WorklistAdapter adapter = new WorklistAdapter(worklist, this);
        ListView view = findViewById(R.id.worklistListView);
        view.setAdapter(adapter);
    }
}
