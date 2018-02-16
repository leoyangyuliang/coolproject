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

public class JournalActivity extends AppCompatActivity{

    private static final String TAG = "JournalActivity";

    private Button btnViewMain;
    private Button btnSaveJournal;
    private Button btnDeleteJournal;
    private EditText editText;
    private String journal;
    private com.example.a49876.myapplication.IOfile IOfile = new com.example.a49876.myapplication.IOfile();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.journal_layout);

        // Define all the widgets
        this.editText = findViewById(R.id.editText);
        this.btnViewMain = (Button) findViewById(R.id.button);
        this.btnSaveJournal = (Button) findViewById(R.id.btnSaveJournal);
        this.btnDeleteJournal =  (Button) findViewById(R.id.btnDeleteJournal);

        // Get current date from intent
        Intent incomingIntent = getIntent();
        final String filename = incomingIntent.getStringExtra("date")+".txt";
        super.setTitle("Journal of "+filename);

        // Render existing journal
        File path = getFilesDir();
        Log.e("path", path.toString());
        File file = new File(path, filename);
        Log.e("show date", filename);
        Log.e("journal activity",IOfile.readFile(file));
        this.editText.setText(IOfile.readFile(file));

        // Back to main display
        this.btnViewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JournalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Save contents of the journal
        this.btnSaveJournal.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                journal = editText.getText().toString();
                Log.e("journal = ", journal);
                File path = getFilesDir();
                File file = new File(path, filename);

                IOfile.writeFile(file,journal);

                AlertDialog alertDialog = new AlertDialog.Builder(JournalActivity.this).create();
                alertDialog.setTitle("Congrats!");
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

        // Delete the current journal
        this.btnDeleteJournal.setOnClickListener(new View.OnClickListener() {
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
