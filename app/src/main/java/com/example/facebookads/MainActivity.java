package com.example.facebookads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import com.example.facebookads.adapter.ViewPagerAdapter;
import com.example.facebookads.fragment.FragmentUtils;
import com.example.facebookads.fragment.Permission;
import com.rd.PageIndicatorView;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PageIndicatorView pageIndicatorView;;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        changgeColorandPermission();
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        pageIndicatorView.setCount(3); // specify total count of indicators
        pageIndicatorView.setSelection(0);
    }
    private void anhXa(){
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        pageIndicatorView=(PageIndicatorView) findViewById(R.id.page_indicator_view);
    }
    private void changgeColorandPermission() {
        Permission.openPermisson(Manifest.permission.READ_EXTERNAL_STORAGE, 111, this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            FragmentUtils.changgeColorStatusBar(this, R.color.blue_facebook);
        }
    }

}