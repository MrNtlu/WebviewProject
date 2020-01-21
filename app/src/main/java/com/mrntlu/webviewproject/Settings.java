package com.mrntlu.webviewproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO CHANGE THEM
        AboutView aboutView = AboutBuilder.with(getContext())
                .setPhoto(R.mipmap.ic_launcher)
                .setCover(R.color.backgroundWhite)
                .setName(R.string.app_name)
                .setSubTitle("<ALT MENÜ YAZISI>")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addFacebookLink("<FACEBOOK>")
                .addTwitterLink("<TWITTER>")
                .addInstagramLink("<INSTAGRAM>")
                .addYoutubeChannelLink("<YOUTUBE>")
                .addGooglePlusLink("<GOOGLE PLUS>")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addFeedbackAction("<ILETISIM LINKI>")
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
        AboutView aboutViewLayout=view.findViewById(R.id.about_view);
        aboutViewLayout.addView(aboutView);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
