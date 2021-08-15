package com.example.projecttraining.homescreen.webdetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<String> url = new MutableLiveData<>();

    public MutableLiveData<String> getUrl() {
        return url;
    }
}
