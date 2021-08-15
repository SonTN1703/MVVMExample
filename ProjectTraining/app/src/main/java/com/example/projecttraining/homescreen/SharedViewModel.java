package com.example.projecttraining.homescreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecttraining.models.SwitchModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> url = new MutableLiveData<>();
    private MutableLiveData<List<SwitchModel>> listFavourSwitch = new MutableLiveData<>();

    public LiveData<String> getUrl() {
        return url;
    }

    public void setUrl(String link) {
        url.setValue(link);
    }

    public void setListFavourSwitch(List<SwitchModel> list) {
        listFavourSwitch.setValue(list);
    }

    public LiveData<List<SwitchModel>> getListFavourSwitch() {
        return listFavourSwitch;
    }
    public void getListFavour(List<SwitchModel> list) {
        List<SwitchModel> listFavour = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChecked()) {
                listFavour.add(list.get(i));
            }
        }
        setListFavourSwitch(listFavour);
    }
}
