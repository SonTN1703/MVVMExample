package com.example.projecttraining.homescreen.home;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecttraining.common.GetDataManager;
import com.example.projecttraining.common.database.DatabaseTableSwitchManager;
import com.example.projecttraining.homescreen.SharedViewModel;
import com.example.projecttraining.models.SwitchModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<SwitchModel>> list = new MutableLiveData<>();
    private List<SwitchModel> mListSwitch;
    private SharedViewModel sharedViewModel;
    private DatabaseTableSwitchManager dbManager;

    public void setSharedViewModel(SharedViewModel sharedViewModel) {
        this.sharedViewModel = sharedViewModel;
    }

    public void setDbManager(DatabaseTableSwitchManager dbManager) {
        this.dbManager = dbManager;
    }

    public void initData(Context context) {
        mListSwitch = new ArrayList<>();
        dbManager.open();
        String jsonFileString = GetDataManager.getJsonFromAssets(context, "switch.json");
        Log.e("data: ", jsonFileString);
        try {

            JSONObject jsonObject = new JSONObject(jsonFileString);
            JSONArray array = jsonObject.getJSONArray("net");

            for (int index = 0; index < array.length(); index++) {
                SwitchModel switchModel = new SwitchModel();
                JSONObject inside = array.getJSONObject(index);
                switchModel.setRequireDesktop(inside.getString("require_desktop"));
                switchModel.setIcon(inside.getString("icon"));
                switchModel.setName(inside.getString("name"));
                switchModel.setLink(inside.getString("link"));
                switchModel.setCategory(inside.getString("category"));
                mListSwitch.add(switchModel);
                dbManager.insertSwitch(switchModel);
            }
        } catch (JSONException e) {
            Log.e("error", e.toString());
        }
        list.postValue(mListSwitch);
        dbManager.close();
    }

    public void initDataFormDatabase(Context context) {
        dbManager.open();
        List<SwitchModel> switchModelList = dbManager.getAllSwitch();
        if (switchModelList.size()>0) {
            mListSwitch = switchModelList;
            list.postValue(mListSwitch);
        } else {
            initData(context);
        }
        dbManager.close();
    }

    public MutableLiveData<List<SwitchModel>> getList() {
        return list;
    }

}
