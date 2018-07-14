package com.mrntlu.webviewproject;

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
                .setPhoto(R.mipmap.ic_launcher)
                .setCover(R.color.backgroundWhite)
                .setName("Bankaciyim.Net")
                .setSubTitle("Türkiye’nin En Büyük Bankacı Platformu")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addFacebookLink("bankaciyimnet")
                .addTwitterLink("webviewproject")
                .addInstagramLink("webviewproject")
                .addYoutubeChannelLink("https://www.youtube.com")
                .addGooglePlusLink("https://www.googleplus.com")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addFeedbackAction("https://www.webviewproject.net/iletisim")
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
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
