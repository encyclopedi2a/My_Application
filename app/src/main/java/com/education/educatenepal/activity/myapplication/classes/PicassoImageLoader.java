package com.education.educatenepal.activity.myapplication.classes;

import android.content.Context;
import android.widget.ImageView;

import com.education.educatenepal.activity.myapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by gokarna on 6/28/15.
 */
public class PicassoImageLoader {
    private Context context;
    public PicassoImageLoader(Context context){
        this.context=context;
    }
    public void homePageImageLoader(ImageView image1,ImageView image2,ImageView image3,ImageView image4,ImageView image5,ImageView image6){
        Picasso.with(context)
                .load("http://tribhuvan-university.edu.np/wp-content/uploads/2014/09/TU-Central-Office-Building-Kirtipur.jpg")
                .placeholder(R.drawable.imagebackground).fit().centerCrop().into(image1);
        Picasso.with(context)
                .load("http://pu.edu.np/university/wp-content/uploads/2013/02/central_office.jpg")
                .placeholder(R.drawable.imagebackground).fit().centerCrop().into(image2);
        Picasso.with(context)
                .load("http://www.puexam.edu.np/pics/slider/pp6_9630.JPG")
                .placeholder(R.drawable.imagebackground).fit().centerCrop().into(image3);
        Picasso.with(context)
                .load("http://www.ku.edu.np/img/5.jpg")
                .placeholder(R.drawable.imagebackground).fit().centerCrop().into(image4);
        Picasso.with(context)
                .load("http://www.lbu.edu.np/images/Slider/Front_4.png")
                .placeholder(R.drawable.imagebackground).fit().centerCrop().into(image5);
        Picasso.with(context)
                .load("http://nsu.edu.np/site/wp-content/uploads/2013/07/IMG_06162-532x286.jpg")
                .placeholder(R.drawable.imagebackground).into(image6);
    }
}
