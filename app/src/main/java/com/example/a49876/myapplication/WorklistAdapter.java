package com.example.a49876.myapplication;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for loading delete button and editText for each entry in worklist.
 */
public class WorklistAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> workEntries;

    public WorklistAdapter(Context context, ArrayList<String> workEntries) {
        super(context, R.layout.test, workEntries);
        this.context = context;
        this.workEntries = workEntries;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.worklist_view, parent, false);

        EditText editTextWork = convertView.findViewById(R.id.editTextWork);
        editTextWork.setText(workEntries.get(position));
        editTextWork.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                workEntries.set(position, s.toString());
            }
        });

        Button btnRemove = convertView.findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parent.getChildCount() > 1) {
                    workEntries.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }
}