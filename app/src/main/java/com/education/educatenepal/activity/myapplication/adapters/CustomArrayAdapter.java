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
import com.education.educatenepal.activity.myapplication.classes.PreferenceSettingValueProvider;

/**
 * Created by gokarna on 6/24/15.
 */
public class CustomArrayAdapter {
    private Context context;
    private View View;
    private String []spinnerData;
    public CustomArrayAdapter(Context context, View View) {
        this.context = context;
        this.View = View;
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
        if(!(new PreferenceSettingValueProvider(context)).provideSharedPreferenceValue()){
            spinnerData = new String[]{"Mechi Zone", "Koshi Zone", "Sagarmatha Zone", "Janakpur Zone", "Narayani Zone", "Bagmati Zone", "Gandaki Zone"
            ,"Dhaulagiri zone","Rapti Zone","Bheri Zone","Karnali Zone","Seti Zone","Mahakali Zone"};
        }else{
            spinnerData=new String[]{"मेची अञ्चल","कोशी अञ्चल","सगरमाथा अञ्चल","जनकपुर अञ्चल","नारायणी अञ्चल","बागमती अञ्चल","गण्डकी अञ्चल" , "धौलागिरी अञ्चल" , "लुम्बिनी अञ्चल " ,
                            "राप्ती अञ्चल " , "भेरी अञ्चल " , "कर्णाली अञ्चल " , "सेति अञ्चल" , "महाकाली अञ्चल "};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_item, spinnerData);
        ((Spinner) View).setAdapter(adapter);
    }
}
