package com.example.projecttraining.homescreen.webdetail;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
;

import com.example.projecttraining.HomeActivity;
import com.example.projecttraining.databinding.FragmentDetailBinding;
import com.example.projecttraining.homescreen.SharedViewModel;


public class FragmentDetail extends Fragment {
    HomeActivity homeActivity;
    DetailViewModel detailViewModel;
    FragmentDetailBinding fragmentDetailBinding;
    SharedViewModel sharedViewModel;

    public static FragmentDetail newInstance() {
        Bundle args = new Bundle();
        FragmentDetail fragmentDetail = new FragmentDetail();
        fragmentDetail.setArguments(args);
        return fragmentDetail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        homeActivity = (HomeActivity) getActivity();
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false);
        fragmentDetailBinding.executePendingBindings();
        return fragmentDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebSettings settings = fragmentDetailBinding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        fragmentDetailBinding.webView.setWebViewClient(new Callback());
        sharedViewModel.getUrl().observe(homeActivity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                fragmentDetailBinding.webView.loadUrl(s);
            }
        });
    }
    private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }

}
