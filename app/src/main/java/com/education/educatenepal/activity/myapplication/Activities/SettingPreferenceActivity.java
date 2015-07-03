package com.education.educatenepal.activity.myapplication.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.fragments.PreferenceFragment;

public class SettingPreferenceActivity extends AppCompatActivity {
    public Context context;
    private Toolbar toolbar;
    private Boolean previousvalue, updatedValue;
    private SharedPreferences settingSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Edu-Nepal Settings");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setTheme(R.style.initialTheme);
        getFragmentManager().beginTransaction().replace(R.id.content_frame,
                new PreferenceFragment()).commit();
        settingSharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
        previousvalue = settingSharedPreference.getBoolean("checkbox_preference", false);
    }

    @Override
    public void onBackPressed() {
        updatedValue = settingSharedPreference.getBoolean("checkbox_preference", false);
        if (previousvalue == updatedValue) {
            Intent myIntent = new Intent(SettingPreferenceActivity.this, DashboardActivity.class);
            startActivity(myIntent);
            finish();
        } else {
            new MaterialProgressDialog(SettingPreferenceActivity.this).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    new MaterialProgressDialog(SettingPreferenceActivity.this).dismiss();
                    Intent myIntent = new Intent(SettingPreferenceActivity.this, DashboardActivity.class);
                    startActivity(myIntent);
                    finish();
                }
            }, 3000);
        }
    }

    private class MaterialProgressDialog extends AlertDialog {
        protected MaterialProgressDialog(final Context context) {
            super(context);
        }

        @Override
        public void show() {
            super.show();
            setContentView(R.layout.customprogressdialog);
        }
    }
}
