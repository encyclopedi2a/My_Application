package com.education.educatenepal.activity.myapplication.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.adapters.CustomArrayAdapter;
import com.education.educatenepal.activity.myapplication.adapters.JSONListBaseAdapter;
import com.education.educatenepal.activity.myapplication.classes.ConnectionManager;
import com.education.educatenepal.activity.myapplication.classes.PreferenceSettingValueProvider;
import com.education.educatenepal.activity.myapplication.json.CollegeNameJson;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;


public class CollegeFragment extends Fragment implements View.OnClickListener {
    private Spinner spinner;
    private SwipeMenuCreator swipeMenuCreator;
    private SwipeMenuListView listView;
    private JSONListBaseAdapter adapter;
    private ImageView internetImage;
    private CircleProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_college, container, false);
        String listPosition = getArguments().getString("position");
        spinner = (Spinner) view.findViewById(R.id.spinner);
        progressBar=(CircleProgressBar)view.findViewById(R.id.progressBar);
        progressBar.setColorSchemeResources(android.R.color.holo_red_light);
        progressBar.setShowProgressText(false);
        internetImage=(ImageView)view.findViewById(R.id.internetImage);
        internetImage.setOnClickListener(this);
        //populating the spinner
        new CustomArrayAdapter(getActivity().getApplicationContext(), spinner, listPosition).populateSpinner();
        //initialising listView
        if (!(new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue())) {
            internetImage.setImageResource(R.drawable.noconnectionenglish);
        }
        listView = (SwipeMenuListView) view.findViewById(R.id.listView);
        new CollegeNameJson(getActivity().getApplicationContext(),listView,progressBar).makeJsonArrayRequest();
        if (new ConnectionManager(getActivity().getApplicationContext()).isConnectionToInternet()) {
            new CollegeNameJson(getActivity().getApplicationContext(),listView,progressBar).makeJsonArrayRequest();
        } else {
            internetImage.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
        swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem webpageItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                webpageItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                webpageItem.setWidth(dp2px(50));
                // set item title
                webpageItem.setIcon(R.drawable.webpage);
                // add to menu
                menu.addMenuItem(webpageItem);

                // create "delete" item
                SwipeMenuItem mapItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                mapItem.setBackground(new ColorDrawable(Color.rgb(0xC9,
                        0xC9, 0xCE)));
                // set item width
                mapItem.setWidth(dp2px(50));
                // set a icon
                mapItem.setIcon(R.drawable.map);
                // add to menu
                menu.addMenuItem(mapItem);
            }
        };
        // set creator
        listView.setMenuCreator(swipeMenuCreator);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        return view;
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        if (new ConnectionManager(getActivity().getApplicationContext()).isConnectionToInternet()) {
            new CollegeNameJson(getActivity().getApplicationContext(),listView,progressBar).makeJsonArrayRequest();
            progressBar.setVisibility(View.VISIBLE);
            internetImage.setVisibility(View.GONE);
        }
    }
}