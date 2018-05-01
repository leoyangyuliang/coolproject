package com.example.a49876.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.ArrayList;

public class AllJournalActivity extends AppCompatActivity {
    private ListView allJournalListView;
    private ArrayList<String> alljournalslist;
    private String[] alljournals;
    private int progress;
    private ProgressBar progressBar;
    private static final String TAG = "AllJournalActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle("All Journals");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_journal_layout);

        //initialization
        progress =0;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        alljournalslist = new ArrayList<String>();
        allJournalListView = (ListView) findViewById(R.id.allJournalListView);

        FileUtils fileutils = new FileUtils();
        File path = getFilesDir();
        File file = new File(path, "AllJournals.bin");
        //get all journals from db
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(LogInActivity.user.getEmail())
                .collection("journals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            Log.e("task issucceess","success");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.e("fetching all jounrals", document.getId() + " => " + document.getData());
                                alljournalslist.add(document.getId());
                                progress++;
                                if(progress/task.getResult().size()==1)
                                    progressBar.setVisibility(View.INVISIBLE);
                            }

                                //convert to string array
                                alljournals = alljournalslist.toArray(new String[alljournalslist.size()]);
                                //create list view
                                ArrayAdapter adapter = new ArrayAdapter(AllJournalActivity.this,
                                        R.layout.textview, alljournals);
                                allJournalListView.setAdapter(adapter);

                                allJournalListView.setOnItemClickListener(
                                        new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView,
                                                                    View view, int i, long l) {
                                                String date = String.valueOf(adapterView.getItemAtPosition(i));
                                                Log.e("show date", date);
                                                Intent intent = new Intent(AllJournalActivity.this,
                                                        JournalActivity.class);
                                                intent.putExtra("date", date);
                                                startActivity(intent);
                                            }
                                        });

                        }
                        else {
                            Log.e("can't get all journals", "journals is empty or task is not success");
                            AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(AllJournalActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                            } else {
                                builder = new AlertDialog.Builder(AllJournalActivity.this);
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
                });


        //get all journals arraylist
//        alljournalslist = (ArrayList) fileutils.readFromBinary(file);

        //convert arraylist to array
//        if (!alljournalslist.isEmpty()) {
//            //change array list to string array
//            alljournals = alljournalslist.toArray(new String[alljournalslist.size()]);
//            //Log.e("alljournals",alljournals[0]);
//
//            //create list view
//            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.textview, alljournals);
//            allJournalListView.setAdapter(adapter);
//
//            allJournalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    String date = String.valueOf(adapterView.getItemAtPosition(i));
//                    Log.e("show date", date);
//                    Intent intent = new Intent(AllJournalActivity.this, JournalActivity.class);
//                    intent.putExtra("date", date);
//                    startActivity(intent);
//                }
//            });
//        } else {
//            AlertDialog.Builder builder;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
//            } else {
//                builder = new AlertDialog.Builder(this);
//            }
//            builder.setTitle("No Journal")
//                    .setMessage("Do you want to write a Journal right now?")
//                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(AllJournalActivity.this, CalendarActivity.class);
//                            startActivity(intent);
//                        }
//                    })
//                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            return;
//                        }
//                    })
//                    .show();
//        }
    }
}
