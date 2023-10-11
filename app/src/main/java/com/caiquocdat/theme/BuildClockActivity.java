package com.caiquocdat.theme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.caiquocdat.theme.databinding.ActivityBuildClockBinding;
import com.caiquocdat.theme.setupclock.ClockWallpaperService;

public class BuildClockActivity extends AppCompatActivity {
    private ActivityBuildClockBinding buildClockBinding;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int YOUR_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildClockBinding = ActivityBuildClockBinding.inflate(getLayoutInflater());
        View view = buildClockBinding.getRoot();
        setContentView(view);
        hideSystemUI();
        Bitmap result= getWallpaperBitmap(this);
//        buildClockBinding.themeImg.setImageBitmap(result);

    }

    public Bitmap getWallpaperBitmap(Activity activity) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(activity);
        Drawable wallpaperDrawable;

        // Check for READ_EXTERNAL_STORAGE permission
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, request for permission
            ActivityCompat.requestPermissions(activity,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // Return null since we don't have permission
            return null;
        } else {
            // Permission has been granted
            wallpaperDrawable = wallpaperManager.getDrawable();
            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, ClockWallpaperService.class));
            startActivityForResult(intent, YOUR_REQUEST_CODE);
        }

        if (wallpaperDrawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) wallpaperDrawable).getBitmap();
        } else {
            int width = wallpaperDrawable.getIntrinsicWidth() <= 0 ? 1 : wallpaperDrawable.getIntrinsicWidth();
            int height = wallpaperDrawable.getIntrinsicHeight() <= 0 ? 1 : wallpaperDrawable.getIntrinsicHeight();

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            wallpaperDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            wallpaperDrawable.draw(canvas);
            return bitmap;
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
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
                    Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(this, ClockWallpaperService.class));
                    startActivityForResult(intent, YOUR_REQUEST_CODE);
                    // Handle the wallpaper bitmap as you want
                } else {
                    // Permission was denied, handle the failure
                }
                return;
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == YOUR_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Người dùng đã cài hình nền thành công
                finish();
            } else {
                // Người dùng đã hủy hoặc thất bại trong việc cài hình nền
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        hideSystemUI();
        super.onResume();
    }
}