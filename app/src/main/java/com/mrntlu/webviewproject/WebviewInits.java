package com.mrntlu.webviewproject;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Build;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

class WebviewInits {

    private WebView webView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private Context context;

    WebviewInits(WebView webView, SwipeRefreshLayout swipeRefreshLayout, ProgressBar progressBar, Context context) {
        this.webView = webView;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.progressBar = progressBar;
        this.context=context;
    }

    void initWeb(){
        webView.getSettings().setJavaScriptEnabled( true );
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCachePath("caches");
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setSavePassword(true);
        webView.getSettings().setSaveFormData(true);
        offlineLoad();//TODO Delete if you don't want offline load

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                context.startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.loadUrl(webView.getUrl());
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
    }

    //TODO Offline Cache Load
    private void offlineLoad(){
        webView.getSettings().setAppCacheMaxSize( 5 * 1024 * 1024 ); // 5MB Size of storage that it will take
        webView.getSettings().setAppCachePath( context.getApplicationContext().getCacheDir().getAbsolutePath() );
        webView.getSettings().setAllowFileAccess( true );
        webView.getSettings().setAppCacheEnabled( true );
        webView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT ); // load online by default

        if ( !WebviewInits.isNetworkAvailable(context) ) { // loading offline
            webView.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        }
    }

    //TODO Checks if network available or not
    static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (connectivityManager!=null) {
                NetworkCapabilities networkCapabilities=connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (networkCapabilities!=null) {
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                        return true;
                    else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                        return true;
                    else
                        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                } else
                    return false;
            }else
                return false;
        }else {
            NetworkInfo activeNetworkInfo = null;
            if (connectivityManager != null)
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }

}
