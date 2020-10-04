package com.example.facebookads.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.facebookads.fragment.FragmentOne;
import com.example.facebookads.fragment.FragmentThree;
import com.example.facebookads.fragment.FragmentTwo;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new FragmentOne();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new FragmentTwo();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new FragmentThree();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
