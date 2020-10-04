package com.example.facebookads.fragment;

import android.app.Activity;
import android.content.Context;

import androidx.core.app.ActivityCompat;

public class Permission {
    public static void openPermisson(String name, int REQUEST_CODE, Context context){
        ActivityCompat.requestPermissions((Activity) context, new String[]{name}, REQUEST_CODE);
    }
}
