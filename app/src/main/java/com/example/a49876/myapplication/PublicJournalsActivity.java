package com.example.a49876.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 49876 on 5/2/2018.
 */

public class PublicJournalsActivity extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseFirestore db;
    private List<ArrayList<String>> mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Stories");
        setContentView(R.layout.recycler_view_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mDataset = new ArrayList<ArrayList<String>>();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //read from database
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("community").document("public_journals");
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        for(String key: document.getData().keySet())
                        {
                            ArrayList<String> l = new ArrayList<>();
                            l.clear();
                            l = (ArrayList<String>)document.getData().get(key);
                            mDataset.add(l);
                        }
                        // specify an adapter (see also next example)
                        mAdapter = new MyAdapter(mDataset);
                        mRecyclerView.setAdapter(mAdapter);

                    } else {
                        Log.e("publicjournalsActivity", "No such document");
                    }
                } else {
                    Log.e("publicjournalsActivity", "get failed with ", task.getException());
                }
            }
        });

    }


}
