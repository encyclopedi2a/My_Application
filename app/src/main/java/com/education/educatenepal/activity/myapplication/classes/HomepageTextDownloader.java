package com.education.educatenepal.activity.myapplication.classes;

import android.os.AsyncTask;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gokarna on 7/4/15.
 */
public class HomepageTextDownloader extends AsyncTask<String, Integer, String> {
    private TextView textView;
    private CircleProgressBar progressBar;

    public HomepageTextDownloader(TextView textView, CircleProgressBar progressBar) {
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setColorSchemeResources(android.R.color.holo_red_light);
        progressBar.setShowProgressText(false);
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        try {
            URL url = new URL("http://gokarna.byethost31.com/homepage.txt");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null;

            while ((line = in.readLine()) != null) {
                //get lines
                result += line;
            }
            in.close();


        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        this.textView.setText(Html.fromHtml(result));
        progressBar.setVisibility(View.GONE);
    }
}
