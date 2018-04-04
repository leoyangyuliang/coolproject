package com.example.a49876.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by Gary on 4/1/18.
 */

public class WorklistAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> worklist;
    private Context context;

    public WorklistAdapter(ArrayList<String> worklist, Context context) {
        this.worklist = worklist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.worklist.size();
    }

    @Override
    public Object getItem(int position) {
        return this.worklist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.worklist_layout, null);
        }
        EditText editTextWork = view.findViewById(R.id.editTextWork);
        Button btnRemove = view.findViewById(R.id.btnDel);

        return view;
    }
}
