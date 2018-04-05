package com.example.a49876.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 49876 on 4/5/2018.
 */
public class CustomAdaptor extends ArrayAdapter<String>
{
    private Context context;
    private List<String> strings;

    public CustomAdaptor (Context context, List<String> strings)
    {
        super(context, R.layout.test, strings);
        this.context = context;
        this.strings = new ArrayList<String>();
        this.strings = strings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.test, parent, false);

        TextView your_first_text_view = (TextView) rowView.findViewById(R.id.text1);
        TextView your_second_text_view = (TextView) rowView.findViewById(R.id.addBtn);

        your_first_text_view.setText(strings.get(position));


        return rowView;
    }
}