package com.education.educatenepal.activity.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.adapters.TabsPagerAdapter;
import com.education.educatenepal.activity.myapplication.classes.PreferenceSettingValueProvider;
import com.education.educatenepal.activity.myapplication.classes.SlidingTabLayout;

public class ViewPagerFragment extends Fragment {
    private ViewPager pager;
    private TabsPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence titles[];
    private int Numboftabs = 4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_pager, null);
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        if(!(new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue()) ){
            titles=new CharSequence[]{"College", "Courses", "Webpage", "Google Map"};
        }else{
            titles=new CharSequence[]{"कलेजहरु","कोर्सेस","वेबपेज","नक्सा"};
        }
        //this value comes from the activity
        String listPosition = getArguments().getString("position");
        adapter = new TabsPagerAdapter(getFragmentManager(), titles, Numboftabs,listPosition);

        /* Assigning ViewPager View and setting the adapter */
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        return view;
    }

}

