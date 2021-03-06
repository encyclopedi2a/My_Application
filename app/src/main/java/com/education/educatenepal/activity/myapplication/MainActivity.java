package com.education.educatenepal.activity.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.education.educatenepal.activity.myapplication.Activities.DashboardActivity;
import com.education.educatenepal.activity.myapplication.classes.PreferenceSettingValueProvider;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    private Animation logoMoveAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        if (!(new PreferenceSettingValueProvider(this)).provideSharedPreferenceValue()) {
            textView.setText("EDU-NEPAL");
        } else {
            textView.setText("एजु-नेपाल");
        }
        imageView = (ImageView) findViewById(R.id.imageView);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.portrait_logo_animation);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.landscape_logo_animation);
        }
        imageView.startAnimation(logoMoveAnimation);
        logoMoveAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //does not perform any action
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
                //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //no animation repeat for this section
            }
        });
    }


}
