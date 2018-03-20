package com.example.a49876.myapplication;

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

import java.io.File;
import java.util.ArrayList;

/**
 * Manages and displays the journals for a date
 */
public class JournalActivity extends AppCompatActivity{
    private static final String TAG = "JournalActivity";
    private Button btnGoMain;
    private Button btnSaveJournal;
    private Button btnDeleteJournal;
    private EditText editText;
    private String journal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_layout);

        // Define all the widgets
        editText = findViewById(R.id.editText);
        btnGoMain = (Button) findViewById(R.id.button);
        btnSaveJournal = (Button) findViewById(R.id.btnSaveJournal);
        btnDeleteJournal =  (Button) findViewById(R.id.btnDeleteJournal);

        // Retrieve the date from incoming intent
        Intent incomingIntent = getIntent();
        final String date = incomingIntent.getStringExtra("date");
        final String filename = date+".txt";
        setTitle("Journal of " + date);

        // Render existing journals

        File path = getFilesDir();
        Log.e("path", path.toString());
        File file = new File(path, filename);
        Log.e("show date", filename);
        Log.e("journal activity", FileUtils.readFile(file));
        editText.setText(FileUtils.readFile(file));

        // View the main page
        btnGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JournalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Button to save the contents of a journal
        btnSaveJournal.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {


                journal = editText.getText().toString();
                Log.e("journal = ", journal);
                File path = getFilesDir();
                File file = new File(path, filename);

                //test if the journal does not exist
                if(!file.exists())
                {
                    FileUtils fileutils = new FileUtils();
                    ArrayList<String> ary = new ArrayList<String>();;
                    if(fileutils.readFromBinary(file)!=null) {
                        ary = (ArrayList) fileutils.readFromBinary(file);
                    }
                    ary.add(date);
                    fileutils.writeToBinary(ary,path,"AllJournals.bin");
                }
                FileUtils.writeFile(file, journal);

                AlertDialog alertDialog = new AlertDialog.Builder(JournalActivity.this).create();
                alertDialog.setTitle("Success!");
                alertDialog.setMessage("Your journal has been saved.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        // Button to delete an existing journal
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
