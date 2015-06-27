package com.education.educatenepal.activity.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.adapters.CustomArrayAdapter;

public class CoursesFragment extends Fragment {
    private Spinner spinner;
    private ListView coursesListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        spinner=(Spinner)view.findViewById(R.id.spinner);
        //populate the spinner from custom layout adapter
        new CustomArrayAdapter(getActivity().getApplicationContext(),spinner).populateSpinner();
        coursesListView=(ListView)view.findViewById(R.id.coursesListView);
        new CustomArrayAdapter(getActivity().getApplicationContext(),coursesListView).populateListView();
        return view;
    }
}
