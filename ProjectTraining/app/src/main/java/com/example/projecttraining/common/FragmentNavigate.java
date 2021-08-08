package com.example.projecttraining.common;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;


public class FragmentNavigate {
    private static void addFragment(Activity activity, @NonNull Fragment fragment){
        ((AppCompatActivity) activity).getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(fragment.getClass().getSimpleName())
                .add(R.id.container,fragment,fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }
    public static void addNewFragment(Activity activity, @NonNull Fragment fragment) {
        AppCompatActivity appCompatActivity = (AppCompatActivity)activity;
        if (appCompatActivity.getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName())==null){
            addFragment(activity,fragment);
        }
    }
}
