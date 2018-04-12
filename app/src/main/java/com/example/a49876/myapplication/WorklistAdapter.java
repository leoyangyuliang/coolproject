package com.example.a49876.myapplication;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for loading delete button and editText for each entry in worklist.
 */
public class WorklistAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> workEntries;

    public WorklistAdapter(Context context, List<String> workEntries) {
        super(context, R.layout.test, workEntries);
        this.context = context;
        this.workEntries = new ArrayList<String>();
        this.workEntries = workEntries;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        view = inflater.inflate(R.layout.worklist_view, parent, false);

        EditText editTextWork = view.findViewById(R.id.editTextWork);
        editTextWork.setText(workEntries.get(position));
        editTextWork.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                workEntries.set(position, s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        Button btnRemove = view.findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workEntries.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}