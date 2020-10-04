package com.example.facebookads;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.facebookads.fragment.FragmentUtils;
import com.example.facebookads.fragment.Permission;

public class DoneActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView imageView;
    private Button btSignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        changgeColorandPermission();
        anhXa();
        new CountDownTimer(8000, 4000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                btSignOut.setVisibility(View.VISIBLE);
            }

        }.start();
        btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
    }
    private void anhXa(){
        progressBar=(ProgressBar) findViewById(R.id.prgressbar_done);
        imageView=(ImageView)findViewById(R.id.im_done);
        btSignOut=(Button) findViewById(R.id.bt_signout);
    }
    private void changgeColorandPermission() {
        Permission.openPermisson(Manifest.permission.READ_EXTERNAL_STORAGE, 111, this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            FragmentUtils.changgeColorStatusBar(this, R.color.blue_facebook);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}