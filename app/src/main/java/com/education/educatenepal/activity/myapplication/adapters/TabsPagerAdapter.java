package com.education.educatenepal.activity.myapplication.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.education.educatenepal.activity.myapplication.fragments.CollegeFragment;
import com.education.educatenepal.activity.myapplication.fragments.CoursesFragment;
import com.education.educatenepal.activity.myapplication.fragments.GoogleMapFragment;
import com.education.educatenepal.activity.myapplication.fragments.WebFragment;

/**
 * Created by gokarna on 6/22/15.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    String position;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public TabsPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb,String position) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.position=position;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Bundle args=new Bundle();
        args.putString("position", this.position);
        if (position == 0) // if the position is 0 we are returning the First tab
        {
            CollegeFragment tab1 = new CollegeFragment();
            tab1.setArguments(args);
            return tab1;
        } else if(position==1) {
            CoursesFragment tab2 = new CoursesFragment();
            tab2.setArguments(args);
            return tab2;
        }else if(position==2){
            WebFragment tab3=new WebFragment();
            tab3.setArguments(args);
            return tab3;
        }else{
            GoogleMapFragment tab4=new GoogleMapFragment();
            tab4.setArguments(args);
            return tab4;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
