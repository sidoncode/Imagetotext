package com.Siddevlops.imagetotext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;


import java.sql.Time;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9094130848994954/1971583337");

        try {
            prepare();
            if(mInterstitialAd.isLoaded()){
                Log.i("ad","add is live");
            }

            ScheduledExecutorService
                    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(mInterstitialAd.isLoaded()){
                                mInterstitialAd.show();
                            }
                            else {
                                Log.i("ad","add is not shown");
                            }
                            prepare();
                        }
                    });

                }
            },10,30, TimeUnit.SECONDS);

        }catch (Exception e){
            e.printStackTrace();
        }





        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView);
        btnNav.setOnNavigationItemSelectedListener(navlistener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout,new Home()).commit();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //return false;
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.item_home:
                    Log.i("selected","Home fragment");
                    selectedFragment = new Home();
                    break;
                case R.id.item_about:
                    selectedFragment = new About();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,selectedFragment).commit();

            return true;
        }
    };


    public void  prepare(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9094130848994954/1971583337");

        mInterstitialAd.loadAd(new AdRequest.Builder().build());


    }




}