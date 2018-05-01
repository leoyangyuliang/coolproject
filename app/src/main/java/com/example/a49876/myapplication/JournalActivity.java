package com.example.a49876.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private FirebaseFirestore db;


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
        //read from database
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("users").document(LogInActivity.user.getEmail())
                .collection("journals").document(date);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        journal = document.getData().get(date).toString();
                        Log.e("journal", journal);
                        editText.setText(journal);
                    } else {
                        editText.setText("");
                        Log.e("reading from DB", "no journal found");
                    }
                } else {
                    Log.e("reading from DB", "task is not success");
                }

            }
        });

//        File path = getFilesDir();
//        Log.e("path", path.toString());
//        File file = new File(path, filename);
//        Log.e("show date", filename);
//        Log.e("journal activity", FileUtils.readFile(file));
//        editText.setText(FileUtils.readFile(file));

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
                FileUtils fileutils = new FileUtils();
                //wrie to database
                Map<String, Object> field = new HashMap<>();
                field.put(date, journal);
                // Add a new document with a specific
                db.collection("users").document(LogInActivity.user.getEmail()).collection("journals").document(date)
                        .set(field, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void documentReference) {
                                Log.e("onsuccess", "DocumentSnapshot added with ID: " + LogInActivity.user.getEmail());
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
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("on failure", "Error adding document", e);
                            }
                        });
//                //path of alljournals
//                File alljournal_file = new File(path,"AllJournals.bin");
//
//                //test if the journal does not exist
//                if(!file.exists())
//                {
//                    FileUtils fileutils = new FileUtils();
//                    ArrayList<String> ary = new ArrayList<String>();
//                    if(alljournal_file.exists()) {
//                        if(fileutils.readFromBinary(alljournal_file)==null){
//                            Log.e(TAG, "reading null" );
//                        }
//                        ary = (ArrayList) fileutils.readFromBinary(alljournal_file);
//                        Log.e("alljournal file exist","true");
//                    }
//                    ary.add(date);
//                    fileutils.writeToBinary(ary,path,"AllJournals.bin");
//                }
//                FileUtils.writeFile(file, journal);
//
//                AlertDialog alertDialog = new AlertDialog.Builder(JournalActivity.this).create();
//                alertDialog.setTitle("Success!");
//                alertDialog.setMessage("Your journal has been saved.");
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();
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
//                                File path = getFilesDir();
//                                File alljournal_file = new File(path,"AllJournals.bin" );
//                                File file = new File(path, filename);
//                                file.delete();
//                                editText.setText("");
//                                if(!file.exists())
//                                {
//                                    FileUtils fileutils = new FileUtils();
//                                    ArrayList<String> ary = new ArrayList<String>();
//                                    if(alljournal_file.exists()) {
//                                        if(fileutils.readFromBinary(alljournal_file)==null){
//                                            Log.e(TAG, "reading null" );
//                                        }
//                                        ary = (ArrayList) fileutils.readFromBinary(alljournal_file);
//                                        Log.e("alljournal file exist","true");
//                                    }
//                                    ary.remove(date);
//                                    fileutils.writeToBinary(ary,path,"AllJournals.bin");
//                                }
                                db.collection("users").document(LogInActivity.user.getEmail())
                                        .collection("journals").document(date).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                        editText.setText("");
                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error deleting document", e);
                                            }
                                        });

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
