package com.example.projecttraining.homescreen.favourite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecttraining.models.SwitchModel;

import java.util.List;

public class FavouriteViewModel extends ViewModel {
    private MutableLiveData<List<SwitchModel>> switchList = new MutableLiveData<>();

    public LiveData<List<SwitchModel>> getSwitchList() {
        return switchList;
    }

    public void setSwitchList(List<SwitchModel> list) {
        switchList.setValue(list);
    }

    public void initData(List<SwitchModel> list) {
        switchList.setValue(list);
    }
}
