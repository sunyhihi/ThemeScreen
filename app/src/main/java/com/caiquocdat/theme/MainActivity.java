package com.caiquocdat.theme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.caiquocdat.theme.databinding.ActivityMainBinding;
import com.caiquocdat.theme.setupclock.ClockWallpaperService;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int YOUR_REQUEST_CODE = 1;
    private String check="false";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        setUpPolicy();
        hideSystemUI();
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, request for permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // Return null since we don't have permission

        } else {
            // Permission has been granted
            check="true";


        }
        mainBinding.policyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PolicyActivity.class);
                startActivity(intent);
            }
        });
        mainBinding.getStartedImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.equals("true")) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setUpPolicy() {
        String text = "<font color=#000000>Read</font> <font color=#868788><b>Terms & Conditions</b></font> <font color=#000000>And</font> <font color=#868788><b>Privacy Policy</b></font>";
        mainBinding.policyTv.setText(Html.fromHtml(text));
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted, you can get the wallpaper now
                    check="true";

                    // Handle the wallpaper bitmap as you want
                } else {
                    // Permission was denied, handle the failure
                    if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Permission is not granted, request for permission
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                        // Return null since we don't have permission

                    } else {
                        // Permission has been granted
                        check="true";


                    }
                }
                return;
            }
        }
    }



    @Override
    protected void onResume() {
        hideSystemUI();
        super.onResume();
    }

    @Override
    public void onBackPressed() {

    }
}