package com.example.projecttraining.homescreen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projecttraining.HomeActivity;
import com.example.projecttraining.common.database.DatabaseTableSwitchManager;
import com.example.projecttraining.databinding.FragmentHomeBinding;
import com.example.projecttraining.homescreen.SharedViewModel;
import com.example.projecttraining.homescreen.SwitchAdapter;
import com.example.projecttraining.models.SwitchModel;

import java.util.List;

public class FragmentHome extends Fragment {
    private HomeActivity homeActivity;
    private FragmentHomeBinding fragmentHomeBinding;
    private HomeViewModel homeViewModel;
    private SwitchAdapter switchAdapter;
    private SharedViewModel sharedViewModel;
    private DatabaseTableSwitchManager dbManager;

    public static FragmentHome newInstance() {
        Bundle args = new Bundle();
        FragmentHome fragmentHome = new FragmentHome();
        fragmentHome.setArguments(args);
        return fragmentHome;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        homeActivity = (HomeActivity) getActivity();
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new HomeViewModel();
        homeViewModel.setSharedViewModel(sharedViewModel);
        fragmentHomeBinding.setHomeViewModel(homeViewModel);
        fragmentHomeBinding.executePendingBindings();
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbManager = new DatabaseTableSwitchManager(getContext());
        homeViewModel.setDbManager(dbManager);
        homeViewModel.initDataFormDatabase(homeActivity.getApplicationContext());
        switchAdapter = new SwitchAdapter(homeActivity, sharedViewModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeActivity.getApplicationContext());
        fragmentHomeBinding.rcShowlistwitch.setLayoutManager(linearLayoutManager);
        homeViewModel.getList().observe(homeActivity, new Observer<List<SwitchModel>>() {
            @Override
            public void onChanged(List<SwitchModel> switchModels) {
                if (switchModels != null) {
                    switchAdapter.setData(switchModels);
                    switchAdapter.notifyDataSetChanged();
                    sharedViewModel.getListFavour(switchModels);
                }
            }
        });
        fragmentHomeBinding.rcShowlistwitch.setAdapter(switchAdapter);
    }

}
