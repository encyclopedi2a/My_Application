package com.education.educatenepal.activity.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.education.educatenepal.activity.myapplication.R;

/**
 * Created by gokarna on 6/21/15.
 */
public class HomePageFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private float initialX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_layout, null);
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
        return view;

    }


}
