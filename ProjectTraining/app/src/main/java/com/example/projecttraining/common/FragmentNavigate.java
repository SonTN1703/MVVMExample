package com.example.projecttraining.common;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.projecttraining.R;
import com.example.projecttraining.register.FragmentRegister;


public class FragmentNavigate {
    private static void addFragment(Activity activity, @NonNull Fragment fragment) {
        ((AppCompatActivity) activity).getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(fragment.getClass().getSimpleName())
                .add(R.id.container, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public static void addNewFragment(Activity activity, @NonNull Fragment fragment) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        if (appCompatActivity.getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName()) == null) {
            addFragment(activity, fragment);
        }else {
            FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    public static void removeFragmentRegister(Activity activity) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        Class<FragmentRegister> cls = FragmentRegister.class;
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentRegister fragmentRegister = (FragmentRegister) appCompatActivity.getSupportFragmentManager().findFragmentByTag(cls.getSimpleName());
        fragmentManager.beginTransaction()
                .remove(fragmentRegister)
                .commitAllowingStateLoss();
        fragmentManager.popBackStack(cls.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
