package com.education.educatenepal.activity.myapplication.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.education.educatenepal.activity.myapplication.R;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;


public class WebFragment extends Fragment {
    WebView webView;
    private CircleProgressBar progressBar;
    private Context context;
    private String url = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        context = getActivity().getApplicationContext();
        //This value comes from following process
        //DashboardActivity->ViewPagerFragment-->WebFragment
        String listPosition = getArguments().getString("position");
        if (listPosition.equals("2")) {
            url = "http://tribhuvan-university.edu.np/";
        } else if (listPosition.equals("3")) {
            url = "http://pu.edu.np/university/";
        } else if (listPosition.equals("4")) {
            url = "http://purbuniv.edu.np/";
        } else if (listPosition.equals("5")) {
            url = "http://www.ku.edu.np/";
        } else if (listPosition.equals("6")) {
            url = "http://www.lbu.edu.np/";
        } else if (listPosition.equals("7")) {
            url = "http://nsu.edu.np/";
        }
        webView = (WebView) view.findViewById(R.id.webview);
        progressBar = (CircleProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setBackgroundColor(Color.WHITE);
        webView.getSettings().setSupportZoom(true);
        webView.setInitialScale(1);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        return view;
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }
}
