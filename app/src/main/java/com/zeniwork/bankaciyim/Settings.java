package com.zeniwork.bankaciyim;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class Settings extends Fragment {

    public static Settings newInstance() {
        return new Settings();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO CHANGE THEM
        AboutView aboutView = AboutBuilder.with(getContext())
                .setPhoto(R.drawable.bankaciyim_logo)
                .setCover(R.drawable.banner1)
                .setName("Bankaciyim.NET")
                .setSubTitle("Türkiye'nin en büyük bankacı platformu.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("user")
                .addFacebookLink("user")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(false)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
        AboutView aboutViewLayout=(AboutView)view.findViewById(R.id.about_view);
        aboutViewLayout.addView(aboutView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
