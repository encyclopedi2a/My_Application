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

/**
 * Created by gokarna on 7/1/15.
 */
public class FacebookLikeFragment extends Fragment {
    private WebView webView;
    private CircleProgressBar progressBar;
    private Context context;
    private String url = "https://www.facebook.com/CurriculumDev";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        context = getActivity().getApplicationContext();
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
