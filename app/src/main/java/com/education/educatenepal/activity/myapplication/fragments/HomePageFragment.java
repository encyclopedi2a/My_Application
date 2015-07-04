package com.education.educatenepal.activity.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.classes.ConnectionManager;
import com.education.educatenepal.activity.myapplication.classes.HomepageTextDownloader;
import com.education.educatenepal.activity.myapplication.classes.PicassoImageLoader;
import com.education.educatenepal.activity.myapplication.classes.PreferenceSettingValueProvider;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by gokarna on 6/21/15.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    private ViewFlipper viewFlipper;
    private float initialX;
    private ImageView internetImage;
    private CircleProgressBar circleProgressBar;
    private TextView content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_layout, null);

        ///////////////////////
        TextView headerText = (TextView) view.findViewById(R.id.heading);
        TextView university1 = (TextView) view.findViewById(R.id.university1);
        TextView university2 = (TextView) view.findViewById(R.id.university2);
        TextView university3 = (TextView) view.findViewById(R.id.university3);
        TextView university4 = (TextView) view.findViewById(R.id.university4);
        TextView university5 = (TextView) view.findViewById(R.id.university5);
        TextView university6 = (TextView) view.findViewById(R.id.university6);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) view.findViewById(R.id.imageView5);
        ImageView imageView6 = (ImageView) view.findViewById(R.id.imageView6);

        //  this textview holds the information from server
        content = (TextView) view.findViewById(R.id.content);
        //loading image at initial start of the fragament in homepage
        new PicassoImageLoader(getActivity().getApplicationContext()).homePageImageLoader(imageView1, imageView2, imageView3, imageView4, imageView5, imageView6);
        //////////////////////////////
        if (!(new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue())) {
            headerText.setText("HIGHER EDUCATION IN NEPAL");
        } else {
            university1.setText("त्रिभुवन विश्वविद्यालय");
            university2.setText("पोखरा विश्वविद्यालय");
            university3.setText("पुर्बन्चल विश्वविद्यालय");
            university4.setText("काठमाडौँ  विश्वविद्यालय");
            university5.setText("लुम्बिनी बुद्ध विश्वविद्यालय");
            university6.setText("महेन्द्र विश्वविद्यालय");
            headerText.setText("नेपालमा उच शिक्षा");
        }
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        viewFlipper.setInAnimation(getActivity().getApplicationContext(), R.anim.in_right);
        viewFlipper.setOutAnimation(getActivity().getApplicationContext(), R.anim.out_left);
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent touchevent) {
                switch (touchevent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = touchevent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float finalX = touchevent.getX();
                        if (initialX > finalX) {
                            if (viewFlipper.getDisplayedChild() == 1)
                                break;
                            viewFlipper.setInAnimation(getActivity().getApplicationContext(), R.anim.in_right);
                            viewFlipper.setOutAnimation(getActivity().getApplicationContext(), R.anim.out_left);

                            viewFlipper.showNext();
                        } else {
                            if (viewFlipper.getDisplayedChild() == 0)
                                break;
                            viewFlipper.setInAnimation(getActivity().getApplicationContext(), R.anim.in_left);
                            viewFlipper.setOutAnimation(getActivity().getApplicationContext(), R.anim.out_right);
                            viewFlipper.showPrevious();
                        }
                        break;
                }
                return true;
            }
        });
        //this image will be activated if the device is not connected to internet
        internetImage = (ImageView) view.findViewById(R.id.internetImage);
        internetImage.setOnClickListener(this);
        if (!(new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue())) {
            internetImage.setImageResource(R.drawable.noconnectionenglish);
        }
        /////////////////////////////////////////////////////////////////
        circleProgressBar = (CircleProgressBar) view.findViewById(R.id.progressBar);
        if (new ConnectionManager(getActivity().getApplicationContext()).isConnectionToInternet()) {
            new HomepageTextDownloader(content, circleProgressBar).execute();
        } else {
            internetImage.setVisibility(View.VISIBLE);
            circleProgressBar.setVisibility(View.GONE);
        }
        return view;

    }

    //this method will populate text according to the viewflipper child position
    private void prepareTextFromViewFlipper(int position, TextView textView) {
        if (!(new PreferenceSettingValueProvider(getActivity().getApplicationContext()).provideSharedPreferenceValue())) {
            switch (position) {
                case 0:
                    textView.setText("Tribhuvan University");
                    break;
                case 1:
                    textView.setText("Pokhara University");
                    break;
                case 2:
                    textView.setText("Purbanchal University");
                    break;
                case 3:
                    textView.setText("Kathmandu University");
                    break;
                case 4:
                    textView.setText("Lumbini Buddha University");
                    break;
                case 5:
                    textView.setText("Mahendra University");
                    break;
            }
        } else {
            switch (position) {

                case 0:
                    textView.setText("");
                    break;
                case 1:
                    textView.setText("");
                    break;
                case 2:
                    textView.setText("");
                    break;
                case 3:
                    textView.setText("");
                    break;
                case 4:
                    textView.setText("");
                    break;
                case 5:
                    textView.setText("");
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (new ConnectionManager(getActivity().getApplicationContext()).isConnectionToInternet()) {
            new HomepageTextDownloader(content, circleProgressBar).execute();
            internetImage.setVisibility(View.GONE);
        }
    }
}
