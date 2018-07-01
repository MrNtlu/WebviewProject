package com.zeniwork.bankaciyim;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (MainPage.getWebView().canGoBack()) { //If it's possible go back on web
                        MainPage.getWebView().goBack();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Emin Misinz?");
                        builder.setMessage("Çıkmak İstiyor Musunuz?");
                        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        builder.setNegativeButton("HAYIR!", new DialogInterface.OnClickListener() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, MainPage.newInstance()).commit();

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

}
