package com.education.educatenepal.activity.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by gokarna on 6/24/15.
 */
public class CustomArrayAdapter {
    private Context context;
    private ListView coursesListView;
    public CustomArrayAdapter(Context context,ListView coursesListView){
        this.context=context;
        this.coursesListView=coursesListView;
    }
    public void populateListView(){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,new String[]{"1","2","3","4","5","6","7"}){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view= super.getView(position, convertView, parent);
                TextView textView=(TextView)view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundColor(Color.parseColor("#d3d3d3"));
                return view;
            }
        };
        coursesListView.setAdapter(adapter);
    }
}
