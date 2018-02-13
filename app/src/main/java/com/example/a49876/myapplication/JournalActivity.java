package com.example.a49876.myapplication;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.a49876.myapplication.IOfile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


/**
 * Created by 49876 on 2/8/2018.
 */

public class JournalActivity extends AppCompatActivity{

    private static final String TAG = "JournalActivity";

    private Button btnGoMain;
    private Button btnSaveJournal;
    private Button btnDeleteJournal;
    private EditText editText;
    private String journal;
    private com.example.a49876.myapplication.IOfile IOfile = new com.example.a49876.myapplication.IOfile();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_layout);

        //define all the widgets
        editText = findViewById(R.id.editText);
        btnGoMain = (Button) findViewById(R.id.button);
        btnSaveJournal = (Button) findViewById(R.id.btnSaveJournal);
        btnDeleteJournal =  (Button) findViewById(R.id.btnDeleteJournal);
        //get date
        Intent incomingIntent = getIntent();
        final String filename = incomingIntent.getStringExtra("date")+".txt";
        setTitle("Journal of "+filename);
        //render existing journal
        File path = getFilesDir();
        Log.e("path", path.toString());
        File file = new File(path, filename);
        Log.e("show date",filename);
        Log.e("journal activity",IOfile.readFile(file));
        editText.setText(IOfile.readFile(file));


        btnGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JournalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnSaveJournal.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                journal = editText.getText().toString();
                Log.e("journal = ", journal);
                File path = getFilesDir();
                File file = new File(path, filename);

                IOfile.writeFile(file,journal);

                AlertDialog alertDialog = new AlertDialog.Builder(JournalActivity.this).create();
                alertDialog.setTitle("Congrats!!");
                alertDialog.setMessage("Your super journal has been saved in internal storage.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK, go away",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

        btnDeleteJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(JournalActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Delete Journal");
                builder.setMessage("Are you sure you want to delete this journal?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                File path = getFilesDir();
                                File file = new File(path, filename);
                                file.delete();
                                editText.setText("");
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


}

}
