package com.education.educatenepal.activity.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.education.educatenepal.activity.myapplication.R;

/**
 * Created by gokarna on 6/24/15.
 */
public class CustomArrayAdapter {
    private Context context;
    private View View;
    private String[] spinnerData;
    private String listPosition;
    private String array[] = null;

    public CustomArrayAdapter(Context context, View View, String listPosition) {
        this.context = context;
        this.View = View;
        this.listPosition = listPosition;
    }

    public void populateListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, new String[]{"1", "2", "3", "4", "5", "6", "7"}) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundColor(Color.parseColor("#d3d3d3"));
                return view;
            }
        };
        ((ListView) View).setAdapter(adapter);
    }

    public void populateSpinner() {
        if (listPosition.equals("2")) {
            array = context.getResources().getStringArray(R.array.tuSpinnerArray);
        } else if (listPosition.equals("3")) {
            array = context.getResources().getStringArray(R.array.puSpinnerArray);
        } else if (listPosition.equals("4")) {
            array = context.getResources().getStringArray(R.array.purSpinnerArray);
        } else if (listPosition.equals("5")) {
            array = context.getResources().getStringArray(R.array.kuSpinnerArray);
        } else if (listPosition.equals("6")) {
            array = context.getResources().getStringArray(R.array.lbuSpinnerArray);
        } else if (listPosition.equals("7")) {
            array = context.getResources().getStringArray(R.array.mahendraSpinnerArray);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_item, array);
        ((Spinner) View).setAdapter(adapter);
    }
}
