package com.education.educatenepal.activity.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.education.educatenepal.activity.myapplication.Beans.RowItem;
import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.adapters.CustomBaseAdapter;

import java.util.ArrayList;
import java.util.List;


public class CollegeFragment extends Fragment {
    private Spinner spinner;
    private ListView listView;
    private String[] collegeName={"1","2","3","4","5","6","7","8","9","10","2","3","4","5","6","7","8","9","10"};
    private List<RowItem> listItems=new ArrayList<RowItem>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_college, container, false);
        spinner=(Spinner)view.findViewById(R.id.spinner);
        listView=(ListView)view.findViewById(R.id.collegeListView);
        for(int i=0; i<collegeName.length;i++){
            RowItem item = new RowItem(collegeName[i]);
            listItems.add(item);
        }
        CustomBaseAdapter adapter = new CustomBaseAdapter(getActivity().getApplicationContext(), listItems);
        listView.setAdapter(adapter);
        return view;
    }
}
