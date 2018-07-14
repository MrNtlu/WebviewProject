package com.mrntlu.webviewproject;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Categories extends Fragment {

    public static Categories newInstance() {
        return new Categories();
    }

    WebView webView;
    static WebView webViewCopy;
    NavigationView navigationView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    AdView adView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    public static WebView getWebView() {
        return webViewCopy;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView=(NavigationView) view.findViewById(R.id.full_nav_view);
        webView=(WebView)view.findViewById(R.id.categories_webview);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefresh_categories);
        progressBar=(ProgressBar)view.findViewById(R.id.categories_progress);
        webViewCopy=webView;
        adView=(AdView)view.findViewById(R.id.categoriesAd);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        WebviewInits webviewInits=new WebviewInits(webView,swipeRefreshLayout,progressBar,getContext());
        webviewInits.initWeb();
        registerForContextMenu(webView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bank_news:
                        webviewSetup("https://www.webviewproject.net/haberler/gundem");
                        break;
                    case R.id.economy:
                        webviewSetup("https://www.webviewproject.net/haberler/ekonomi");
                        break;
                    case R.id.credits:
                        webviewSetup("https://www.webviewproject.net/haberler/banka-kredileri");
                        break;
                    case R.id.carreer:
                        webviewSetup("https://www.webviewproject.net/haberler/kariyer");
                        break;
                    case R.id.pano:
                        webviewSetup("https://www.webviewproject.net/haberler/pano");
                        break;
                    case R.id.bank_hire:
                        webviewSetup("https://www.webviewproject.net/haberler/banka-personel-alimlari");
                        break;
                    case R.id.kamu_hire:
                        webviewSetup("https://www.webviewproject.net/haberler/kamu-is-ilanlari");
                        break;
                    case R.id.other_hire:
                        webviewSetup("https://www.webviewproject.net/haberler/diger-is-ilanlari");
                        break;
                }
                return true;
            }
        });
    }

    void webviewSetup(String url){
        webView.loadUrl(url);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        navigationView.setVisibility(View.GONE);
        adView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
