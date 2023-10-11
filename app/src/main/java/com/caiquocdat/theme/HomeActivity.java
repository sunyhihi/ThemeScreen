package com.caiquocdat.theme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.caiquocdat.theme.databinding.ActivityHomeBinding;
import com.caiquocdat.theme.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding homeBinding;
    private String check = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = homeBinding.getRoot();
        setContentView(view);
        hideSystemUI();

        homeBinding.item2Rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(HomeActivity.this,BuildClockActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 7);
                startActivity(intent);
            }
        });
        homeBinding.luxTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 0);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.classTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 1);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.ficTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 2);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.houlTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 3);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.modTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 4);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.luxTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 5);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.favouriteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 9);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.tradiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ThemeActivity.class);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.clocklTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 7);
                startActivity(intent);
                homeBinding.showMoreLinear.setVisibility(View.GONE);
            }
        });
        homeBinding.showMoreLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBinding.showMoreLinear.setVisibility(View.GONE);
                homeBinding.item1Rel.setEnabled(true);
                homeBinding.item2Rel.setEnabled(true);
            }
        });
      homeBinding.moreImg.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              if (check.equals("false")) {
                  homeBinding.showMoreLinear.setVisibility(View.VISIBLE);
                  homeBinding.item1Rel.setEnabled(false);
                  homeBinding.item2Rel.setEnabled(false);
                  check="true";
              }else{
                  homeBinding.showMoreLinear.setVisibility(View.GONE);
                  homeBinding.item1Rel.setEnabled(true);
                  homeBinding.item2Rel.setEnabled(true);
                  check="false";
              }
              return false;
          }
      });
        homeBinding.seeAllImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(HomeActivity.this,BuildClockActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(HomeActivity.this, DetailThemeActivity.class);
                intent.putExtra("potition", 8);
                startActivity(intent);
            }
        });
        homeBinding.backRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        homeBinding.item1Rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ThemeActivity.class);
                startActivity(intent);
            }
        });
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
    protected void onResume() {
        hideSystemUI();
        homeBinding.item1Rel.setEnabled(true);
        homeBinding.item2Rel.setEnabled(true);
        super.onResume();
    }

    @Override
    public void onBackPressed() {

    }

}