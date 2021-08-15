package com.example.projecttraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.projecttraining.common.FragmentNavigate;
import com.example.projecttraining.homescreen.favourite.FragmentFavourite;
import com.example.projecttraining.homescreen.home.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView mBtNavHomeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBtNavHomeActivity = findViewById(R.id.btnav_main_activity);
        mBtNavHomeActivity.setOnNavigationItemSelectedListener(navListener);
        FragmentHome fragmentHome = FragmentHome.newInstance();
        FragmentNavigate.addNewFragment(this, fragmentHome);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    FragmentHome fragmentHome = FragmentHome.newInstance();
                    FragmentNavigate.addNewFragment(HomeActivity.this, fragmentHome);
                    break;
                case R.id.action_favourite:
                    FragmentFavourite fragmentFavourite = FragmentFavourite.newInstance();
                    FragmentNavigate.addNewFragment(HomeActivity.this, fragmentFavourite);
                    break;
            }
            return true;
        }
    };
}