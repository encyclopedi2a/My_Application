package com.education.educatenepal.activity.myapplication.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.education.educatenepal.activity.myapplication.fragments.PreferenceFragment;

public class SettingPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED));
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PreferenceFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Applying Settings");
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Intent myIntent = new Intent(SettingPreferenceActivity.this, DashboardActivity.class);
                startActivity(myIntent);
                finish();
            }
        }, 3000);
    }

}
