package com.education.educatenepal.activity.myapplication.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.fragments.PreferenceFragment;

public class SettingPreferenceActivity extends AppCompatActivity {
    public Context context;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.tool_bar,null);
        toolbar = (Toolbar)view.findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PreferenceFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        new MaterialProgressDialog(SettingPreferenceActivity.this).show();
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                new MaterialProgressDialog(SettingPreferenceActivity.this).dismiss();
                Intent myIntent = new Intent(SettingPreferenceActivity.this, DashboardActivity.class);
                startActivity(myIntent);
                finish();
            }
        },3000);

    }

    private  class MaterialProgressDialog extends AlertDialog {
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
