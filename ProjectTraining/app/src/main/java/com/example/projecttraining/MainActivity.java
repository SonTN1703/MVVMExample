package com.example.projecttraining;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.common.FragmentNavigate;
import com.example.projecttraining.login.FragmentLogin;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentLogin fragmentLogin = FragmentLogin.newInstance();
        FragmentNavigate.addNewFragment(this, fragmentLogin);
    }
}