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
import com.education.educatenepal.activity.myapplication.json.PUCollegeNameJson;
import com.education.educatenepal.activity.myapplication.json.TUCollegeNameJson;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;


public class CollegeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private SwipeMenuCreator swipeMenuCreator;
    private SwipeMenuListView listView;
    private JSONListBaseAdapter adapter;
    private ImageView internetImage;
    private CircleProgressBar progressBar;
    private String listPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPosition = getArguments().getString("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_college, container, false);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        progressBar = (CircleProgressBar) view.findViewById(R.id.progressBar);
        internetImage = (ImageView) view.findViewById(R.id.internetImage);
        listView = (SwipeMenuListView) view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //populating the spinner
        progressBar.setColorSchemeResources(android.R.color.holo_red_light);
        progressBar.setShowProgressText(false);
        internetImage.setOnClickListener(this);
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
        new CustomArrayAdapter(getActivity().getApplicationContext(), spinner, listPosition).populateSpinner();
        //initialising listView
        if (!(new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue())) {
            internetImage.setImageResource(R.drawable.noconnectionenglish);
        }
        new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("medical");
        if (new ConnectionManager(getActivity().getApplicationContext()).isConnectionToInternet()) {
            new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("medical");
        } else {
            internetImage.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        if (new ConnectionManager(getActivity().getApplicationContext()).isConnectionToInternet()) {
            new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("medical");
            progressBar.setVisibility(View.VISIBLE);
            internetImage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (Integer.parseInt(listPosition)) {
            case 2:
                switch (position) {
                    case 0:
                        new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("medical");
                        break;
                    case 1:
                        new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("engineering");
                        break;
                    case 2:
                        new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("bba");
                        break;
                    case 3:
                        new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("bsccsit");
                        break;
                    case 4:
                        new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("bim");
                        break;
                    case 5:
                        new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("bbs");
                        break;
                    case 6:
                        new TUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("bbm");
                        break;
                }
                break;
            case 3:
                switch (position) {
                    case 0:
                        new PUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("bba");
                        break;
                    case 1:
                        new PUCollegeNameJson(getActivity().getApplicationContext(), listView, progressBar).makeJsonArrayRequest("bba-bi");
                        break;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}