package com.mrntlu.webviewproject;

import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Categories extends Fragment implements FragmentOnBackPressed{

    public static Categories newInstance() {
        return new Categories();
    }

    private WebView webView;
    private NavigationView navigationView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdView adView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView=view.findViewById(R.id.full_nav_view);
        webView=view.findViewById(R.id.categories_webview);
        swipeRefreshLayout=view.findViewById(R.id.swipeRefresh_categories);
        ProgressBar progressBar = view.findViewById(R.id.categories_progress);
        adView=view.findViewById(R.id.categoriesAd);

        setAds();

        WebviewInits webviewInits=new WebviewInits(webView,swipeRefreshLayout, progressBar,getContext());
        webviewInits.initWeb();
        registerForContextMenu(webView);

        //TODO Categories Menu Items
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bank_news:
                        webviewSetup("<LINK BURAYA>");
                        break;
                    case R.id.economy:
                        webviewSetup("<LINK BURAYA>");
                        break;
                    case R.id.credits:
                        webviewSetup("<LINK BURAYA>");
                        break;
                    case R.id.carreer:
                        webviewSetup("<LINK BURAYA>");
                        break;
                    case R.id.pano:
                        webviewSetup("<LINK BURAYA>");
                        break;
                    case R.id.bank_hire:
                        webviewSetup("<LINK BURAYA>");
                        break;
                    case R.id.kamu_hire:
                        webviewSetup("<LINK BURAYA>");
                        break;
                    case R.id.other_hire:
                        webviewSetup("<LINK BURAYA>");
                        break;
                }
                return true;
            }
        });
    }

    private void setAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void webviewSetup(String url){
        webView.loadUrl(url);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        navigationView.setVisibility(View.GONE);
        adView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
    }
}
