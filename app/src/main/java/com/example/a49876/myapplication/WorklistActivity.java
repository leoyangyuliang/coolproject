package com.example.a49876.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Manage work list for the selected date
 */
public class WorklistActivity extends AppCompatActivity implements Serializable {
    private FirebaseFirestore db;
    private Button btnSave;
    private WorklistAdapter adapter;
    private ArrayList<String> strings;
    private ListView listView;
    private Map<String,Object> field;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Worklist");
        setContentView(R.layout.worklist_layout);

        //initialization
        btnSave = findViewById(R.id.btnSave);
        strings = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("users").document(LogInActivity.user.getEmail())
                .collection("worklists").document("worklist");
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()&& !task.getResult().getData().isEmpty()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        adapter = (WorklistAdapter) document.getData().get("worklistID");
                        listView = findViewById(R.id.worklistListView);
                        listView.setAdapter(adapter);
                        Log.e("strings ",":"+strings);
                    } else {
                        Log.e("reading from DB", "no worklist found");
                    }
                } else {
                    strings.add("to-do");
                    adapter = new WorklistAdapter(WorklistActivity.this, strings);
                    listView = findViewById(R.id.worklistListView);
                    listView.setAdapter(adapter);
                    Log.e("reading from DB", "task is not success");
                }

            }
        });




        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add("to-do");
            }
        });


        //update database
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //wrie to database
                field = new HashMap<>();
                field.put("worklistID",adapter);

                // Add a new document with a specific
                db.collection("users").document(LogInActivity.user.getEmail())
                        .collection("worklists").document("worklist")
                        .set(field, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void documentReference) {
                                AlertDialog alertDialog = new AlertDialog.Builder(WorklistActivity.this).create();
                                alertDialog.setTitle("Success!");
                                alertDialog.setMessage("Your worklists has been saved.");
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
            }
        });

    }
}
