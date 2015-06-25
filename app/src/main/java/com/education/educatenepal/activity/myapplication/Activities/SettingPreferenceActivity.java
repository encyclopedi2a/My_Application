package com.education.educatenepal.activity.myapplication.Activities;

import android.app.Activity;
import android.os.Bundle;

import com.education.educatenepal.activity.myapplication.fragments.PreferenceFragment;

public class SettingPreferenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PreferenceFragment()).commit();
    }

}
