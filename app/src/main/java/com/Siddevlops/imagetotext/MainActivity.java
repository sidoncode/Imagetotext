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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;


import java.util.List;

/**
    public class MainActivity extends AppCompatActivity {





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


        }

        private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                 imageBitmap = (Bitmap) extras.get("data");
                imgview1.setImageBitmap(imageBitmap);
            }
        }

        protected void Image_to_text(){
            FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);

            FirebaseVisionTextDetector firebaseVisionTextDetector=  FirebaseVision.getInstance().getVisionTextDetector();
            firebaseVisionTextDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                @Override
                public void onSuccess(FirebaseVisionText firebaseVisionText) {
                    Log.i("image to txt Sucess","on image read Success");
                    Image_to_text(firebaseVisionText);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.i("image to txt fail","on fail of image to text");
                    e.printStackTrace();

                }
            });
        }

        public void Image_to_text(FirebaseVisionText firebaseVisionText) {

            List<FirebaseVisionText.Block> blockList = firebaseVisionText.getBlocks();
            if(blockList.size() == 0){
                Toast.makeText(getApplicationContext(),"No Text Found in Image",Toast.LENGTH_SHORT).show();
            }
            else {
                for(FirebaseVisionText.Block block: firebaseVisionText.getBlocks()){
                    String text = block.getText();
                        txtview1.setText(text);
                        Log.i("image to txt converted",text);
                }
            }

        }


    }**/


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



}