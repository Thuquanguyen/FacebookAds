package com.example.facebookads.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class FragmentUtils {
    public static Fragment getCurrentFragment(FragmentManager manager){
        List<Fragment> fragments= manager.getFragments();
        if ( fragments != null){
            for ( int i = fragments.size()-1; i >=0; i--){
                Fragment fragment = fragments.get(i);
                if ( fragment == null){
                    continue;
                }
                if ( fragment.isVisible()){
                    return fragment;
                }
            }
        }
        return null;
    }
    public static void setStatusBarGradiant(Activity activity, int shapecolor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(shapecolor);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void changgeColorStatusBar(Activity activity, int color){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(activity, color));
        }
    }
}
