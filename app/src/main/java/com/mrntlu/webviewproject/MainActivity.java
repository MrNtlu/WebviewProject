package com.mrntlu.webviewproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.onesignal.OneSignal;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    Dialog splashScreenDialog;
    BottomNavigationView bottomNavigationView;
    BroadcastReceiver broadcastReceiver;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (MainPage.getWebView().canGoBack()) {
                        MainPage.getWebView().goBack();
                    }
                    else if (Categories.getWebView()!=null && Categories.getWebView().canGoBack()){
                        Categories.getWebView().goBack();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle(R.string.emin_misin);
                        builder.setMessage(R.string.cikmak_istiyor_musun);
                        builder.setPositiveButton(R.string.evet, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        builder.setNegativeButton(R.string.hayir, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void checkInternet(){
        IntentFilter intentFilter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (isNetworkAvailable()){
                    Toasty.success(context,getString(R.string.baglanti_basarili), Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    Toasty.error(context,getString(R.string.baglanti_basarisiz),Toast.LENGTH_LONG).show();
                }
            }
        };
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, MainPage.newInstance()).commit();
        showSplash();
        checkInternet();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectItem(item);
                return false;
            }
        });
    }

    void selectItem(MenuItem menuItem){
        Fragment fragment=null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.main_page:
                fragmentClass=MainPage.class;
                break;
            case R.id.settings:
                fragmentClass=Settings.class;
                break;
            case R.id.categories:
                fragmentClass=Categories.class;
                break;

            default:
                fragmentClass=MainPage.class;
        }
        try{
            fragment=(Fragment)fragmentClass.newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout,fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
    }

    void showSplash(){
        splashScreenDialog=new Dialog(this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        splashScreenDialog.setContentView(R.layout.splash_screen);
        splashScreenDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                splashScreenDialog.dismiss();
            }
        }, 4000);
    }

}
