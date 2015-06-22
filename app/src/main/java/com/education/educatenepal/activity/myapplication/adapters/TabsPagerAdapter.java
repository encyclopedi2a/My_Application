package com.education.educatenepal.activity.myapplication.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.education.educatenepal.activity.myapplication.fragments.CollegeFragment;
import com.education.educatenepal.activity.myapplication.fragments.CoursesFragment;
import com.education.educatenepal.activity.myapplication.fragments.GoogleMapFragment;
import com.education.educatenepal.activity.myapplication.fragments.WebFragment;

/**
 * Created by gokarna on 6/22/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Top Rated fragment activity
                return new CoursesFragment();
            case 1:
                // Games fragment activity
                return new CollegeFragment();
            case 2:
                // Movies fragment activity
                return new WebFragment();
            case 3:
                return new GoogleMapFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
