package com.education.educatenepal.activity.myapplication.classes;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.education.educatenepal.activity.myapplication.R;

/**
 * Created by gokarna on 7/2/15.
 */
public class DisclaimerDialogBox {
    private Context context;
    public DisclaimerDialogBox(Context context){
        this.context=context;
    }
    public void showDialog(){
        new MaterialDialog.Builder(context)
                .title("Disclaimer")
                .positiveColorRes(R.color.ColorPrimary)
                .content(R.string.disclaimer_content)
                .positiveText("OK!")
                .show();
    }
}
