package com.zeniwork.bankaciyim;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;


public class Categories extends Fragment {

    public static Categories newInstance() {
        return new Categories();
    }

    WebView webView;
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView=(NavigationView) view.findViewById(R.id.full_nav_view);
        webView=(WebView)view.findViewById(R.id.categories_webview);
        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefresh_categories);
        final ProgressBar progressBar=(ProgressBar)view.findViewById(R.id.categories_progress);

        webView.getSettings().setJavaScriptEnabled( true );
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCachePath("caches");
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setSavePassword(true);
        webView.getSettings().setSaveFormData(true);

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (!swipeRefreshLayout.isRefreshing()){ //If you didn't refresh the page by using swiperefresh it will show progressbar.
                    progressBar.setVisibility(View.VISIBLE);
                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://play.google.com/") || url.startsWith("http://play.google.com/")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        Activity host = (Activity) view.getContext();
                        host.startActivity(intent);
                        return true;
                    } catch (ActivityNotFoundException e) {
                        // Google Play app is not installed, you may want to open the app store link
                        Uri uri = Uri.parse(url);
                        view.loadUrl("https://play.google.com/store/apps/" + uri.getHost() + "?" + uri.getQuery());
                        return false;
                    }
                }
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.INVISIBLE); //It will hide progressbar because our page loaded.
                if (swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false); //This will hide swiperefresh icon if we refreshed.
                }
                super.onPageFinished(view, url);
            }
        });        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        registerForContextMenu(webView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bank_news:
                        webView.loadUrl("https://www.bankaciyim.net/haberler/gundem");
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        navigationView.setVisibility(View.GONE);
                        break;
                    case R.id.economy:
                        webView.loadUrl("https://www.bankaciyim.net/haberler/ekonomi");
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        navigationView.setVisibility(View.GONE);
                        break;
                    case R.id.credits:
                        webView.loadUrl("https://www.bankaciyim.net/haberler/banka-kredileri");
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        navigationView.setVisibility(View.GONE);
                        break;
                    case R.id.carreer:
                        webView.loadUrl("https://www.bankaciyim.net/haberler/kariyer");
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        navigationView.setVisibility(View.GONE);
                        break;
                    case R.id.pano:
                        webView.loadUrl("https://www.bankaciyim.net/haberler/pano");
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        navigationView.setVisibility(View.GONE);
                        break;
                    case R.id.bank_hire:
                        webView.loadUrl("https://www.bankaciyim.net/haberler/banka-personel-alimlari");
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        navigationView.setVisibility(View.GONE);
                        break;
                    case R.id.kamu_hire:
                        webView.loadUrl("https://www.bankaciyim.net/haberler/kamu-is-ilanlari");
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        navigationView.setVisibility(View.GONE);
                        break;
                    case R.id.other_hire:
                        webView.loadUrl("https://www.bankaciyim.net/haberler/diger-is-ilanlari");
                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                        navigationView.setVisibility(View.GONE);
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
