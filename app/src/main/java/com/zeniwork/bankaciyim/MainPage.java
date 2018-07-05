package com.zeniwork.bankaciyim;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainPage extends Fragment {

    private final String web_url="http://www.bankaciyim.net/";
    WebView webView;
    AdView adView;
    static WebView webViewCopy;

    public static MainPage newInstance() {
        return new MainPage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static WebView getWebView() {
        return webViewCopy;
    }

    public static void setWeb(String url){
        webViewCopy.loadUrl(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mainpage_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView=(WebView)view.findViewById(R.id.myWebview);
        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
        final ProgressBar progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        webViewCopy=webView;
        adView=(AdView)view.findViewById(R.id.adMob);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        WebviewInits webviewInits=new WebviewInits(webView,swipeRefreshLayout,progressBar,getContext());
        webviewInits.initWeb();

        registerForContextMenu(webView);
        webView.loadUrl(web_url);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
