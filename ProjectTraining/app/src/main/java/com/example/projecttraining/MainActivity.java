package com.example.projecttraining;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.common.FragmentNavigate;
import com.example.projecttraining.common.database.DatabaseHelper;
import com.example.projecttraining.login.FragmentLogin;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        FragmentLogin fragmentLogin = FragmentLogin.newInstance();
        FragmentNavigate.addNewFragment(this, fragmentLogin);
    }
}