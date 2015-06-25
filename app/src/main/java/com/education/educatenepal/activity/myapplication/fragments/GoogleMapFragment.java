package com.education.educatenepal.activity.myapplication.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.education.educatenepal.activity.myapplication.R;


public class GoogleMapFragment extends Fragment {
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_googlemap, container, false);
        textView=(TextView)view.findViewById(R.id.nepali);
        Typeface fontHindi = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Ananda Lipi Bold Cn Bt.ttf");
        textView.setTypeface(fontHindi);
        return view;
    }


}
