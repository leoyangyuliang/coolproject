package com.example.a49876.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.Serializable;

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
        setContentView(R.layout.work_list_layout);

        // Add a text line to put work to-do
        this.btnAdd = findViewById(R.id.btnAdd);
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textLine = new EditText(WorklistActivity.this);
                LinearLayout layout = findViewById(R.id.linearLayout);
                layout.addView(textLine);
            }
        });


        // Remove a text line
        this.btnRemove = findViewById(R.id.btnRemove);
        this.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = findViewById(R.id.linearLayout);
                if (layout.getChildCount() > 0) {
                    layout.removeViewAt(0);
                }
            }
        });

        // Save contents of all text lines into file
        this.btnSave = findViewById(R.id.btnSave);
        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
