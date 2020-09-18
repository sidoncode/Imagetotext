package com.Siddevlops.imagetotext;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import gr.net.maroulis.library.EasySplashScreen;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //NOActi

        EasySplashScreen config = new EasySplashScreen(LauncherActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withAfterLogoText("after logo")
                .withBeforeLogoText("before logo")
                .withBackgroundColor(Color.parseColor("#1a1b29"))
                .withFooterText("Header")
                .withHeaderText("Footer")
                .withLogo(R.drawable.common_google_signin_btn_icon_dark);

        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);








    }
}
