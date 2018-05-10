package com.example.a49876.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {
    private List<ArrayList<String>> mDataset;
    private Context context;
    private ImageButton imageButton;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView email;
        public TextView date;
        public TextView journal;
        public TextView numberOfLikes;
        public CardView cv;

        public ViewHolder(View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.card_view);
            email = v.findViewById(R.id.email);
            date = v.findViewById(R.id.date);
            journal= v.findViewById(R.id.journal);
            numberOfLikes = v.findViewById(R.id.numberOfLikes);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public StoriesAdapter(List<ArrayList<String>> myDataset, Context context) {
        this.mDataset = myDataset;
        for(int i =0; i < mDataset.size(); i++){
            for(int j=i; j<myDataset.size(); j++){
            if(Integer.parseInt(myDataset.get(i).get(3))<Integer.parseInt(myDataset.get(j).get(3)))
            {
                ArrayList<String> s  = myDataset.get(i);
                myDataset.set(i,myDataset.get(j));
                myDataset.set(j,s);
            }
        }
        }

        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent,
                false);
        ViewHolder vh = new ViewHolder(v);
        imageButton = v.findViewById(R.id.like);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.email.setText(mDataset.get(position).get(0));
        holder.date.setText(mDataset.get(position).get(1));
        holder.journal.setText(mDataset.get(position).get(2));
        holder.numberOfLikes.setText(mDataset.get(position).get(3));

        Log.e("onBindviewHolder",Integer.toString(position));
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v.findViewById(R.id.like).getBackground().getConstantState()
                        == ContextCompat.getDrawable(context,R.drawable.heart).getConstantState()){
                v.findViewById(R.id.like).setBackgroundResource(R.drawable.redheart);
                String s = holder.numberOfLikes.getText().toString();
                int i  = Integer.parseInt(s);
                i++;
                s = Integer.toString(i);
                holder.numberOfLikes.setText(s);
                String id = mDataset.get(position).get(0)+mDataset.get(position).get(1);
                mDataset.get(position).remove(3);
                mDataset.get(position).add(3,s);
                Map<String, Object> field = new HashMap<>();
                field.put(id, mDataset.get(position));
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("community").document("public_journals")
                        .set(field, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void documentReference) {
                                Log.e("onsuccess", "updated likes ID: " + LogInActivity.user.getEmail());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("on failure", "Error updating ID", e);
                            }
                        });
                Toast.makeText(context,"Like!", Toast.LENGTH_LONG).show();

            }else if(v.findViewById(R.id.like).getBackground().getConstantState()
                        == ContextCompat.getDrawable(context,R.drawable.redheart).getConstantState())
                {
                    v.findViewById(R.id.like).setBackgroundResource(R.drawable.heart);
                    String s = holder.numberOfLikes.getText().toString();
                    int i  = Integer.parseInt(s);
                    i--;
                    s = Integer.toString(i);
                    holder.numberOfLikes.setText(s);
                    String id = mDataset.get(position).get(0)+mDataset.get(position).get(1);
                    mDataset.get(position).remove(3);
                    mDataset.get(position).add(3,s);
                    Map<String, Object> field = new HashMap<>();
                    field.put(id, mDataset.get(position));
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("community").document("public_journals")
                            .set(field, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void documentReference) {
                                    Log.e("onsuccess", "updated likes ID: " + LogInActivity.user.getEmail());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("on failure", "Error updating ID", e);
                                }
                            });
                    Toast.makeText(context,"I Don't Like It No More!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}