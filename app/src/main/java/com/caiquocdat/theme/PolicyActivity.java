package com.caiquocdat.theme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.caiquocdat.theme.databinding.ActivityHomeBinding;
import com.caiquocdat.theme.databinding.ActivityMainBinding;
import com.caiquocdat.theme.databinding.ActivityPolicyBinding;

public class PolicyActivity extends AppCompatActivity {
    private ActivityPolicyBinding policyBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        policyBinding = ActivityPolicyBinding.inflate(getLayoutInflater());
        View view = policyBinding.getRoot();
        setContentView(view);
        setUpPolicy();
        hideSystemUI();
        policyBinding.getStartedImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PolicyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setUpPolicy() {
        String text = "<font color=#FFFFFF>Read</font> <font color=#AC7F66><b>Terms & Conditions</b></font> <font color=#FFFFFF>And</font> <font color=#AC7F66><b>Privacy Policy</b></font>";
        policyBinding.policyTv.setText(Html.fromHtml(text));
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
    protected void onResume() {
        hideSystemUI();
        super.onResume();
    }
    @Override
    public void onBackPressed() {

    }
}