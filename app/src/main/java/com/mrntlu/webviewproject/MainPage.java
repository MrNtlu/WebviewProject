package com.mrntlu.webviewproject;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainPage extends Fragment implements FragmentOnBackPressed{
    //TODO Website Link
    private final String web_url="<WEBSITE LINKI>";
    private WebView webView;
    private AdView adView;

    public static MainPage newInstance() {
        return new MainPage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mainpage_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView=view.findViewById(R.id.myWebview);
        final SwipeRefreshLayout swipeRefreshLayout=view.findViewById(R.id.swipeRefreshLayout);
        final ProgressBar progressBar=view.findViewById(R.id.progressBar);
        adView=view.findViewById(R.id.adMob);

        setAds();
        WebviewInits webviewInits=new WebviewInits(webView,swipeRefreshLayout,progressBar,getContext());
        webviewInits.initWeb();

        registerForContextMenu(webView);
        webView.loadUrl(web_url);
    }

    private void setAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }
}
