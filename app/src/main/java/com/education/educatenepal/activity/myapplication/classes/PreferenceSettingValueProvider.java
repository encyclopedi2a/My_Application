package com.education.educatenepal.activity.myapplication.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by gokarna on 6/26/15.
 */
public class PreferenceSettingValueProvider {
    private Context context;
    public PreferenceSettingValueProvider(Context context){
        this.context=context;
    }
    public boolean provideSharedPreferenceValue(){
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean checkbox_preference = mySharedPreferences.getBoolean("checkbox_preference", false);
        return checkbox_preference;
    }
}
