package com.education.educatenepal.activity.myapplication.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.educatenepal.activity.myapplication.R;

/**
 * Created by gokarna on 6/25/15.
 */
public class PreferenceFragment extends android.preference.PreferenceFragment {
    private CheckBoxPreference checkBoxPreference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.setting_preference);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        checkBoxPreference = (CheckBoxPreference) getPreferenceManager().findPreference("checkbox_preference");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
