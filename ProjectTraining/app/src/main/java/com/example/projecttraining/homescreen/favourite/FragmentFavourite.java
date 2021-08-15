package com.example.projecttraining.homescreen.favourite;

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
import com.example.projecttraining.databinding.FragmentFavouriteBinding;
import com.example.projecttraining.homescreen.SharedViewModel;
import com.example.projecttraining.homescreen.SwitchAdapter;
import com.example.projecttraining.models.SwitchModel;

import java.util.List;

public class FragmentFavourite extends Fragment {
    FragmentFavouriteBinding fragmentFavouriteBinding;
    FavouriteViewModel favouriteViewModel;
    SharedViewModel sharedViewModel;
    HomeActivity homeActivity;
    SwitchAdapter switchAdapter;

    public static FragmentFavourite newInstance() {
        FragmentFavourite fragmentFavourite = new FragmentFavourite();
        Bundle args = new Bundle();
        fragmentFavourite.setArguments(args);
        return fragmentFavourite;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        homeActivity = (HomeActivity) getActivity();
        switchAdapter = new SwitchAdapter(homeActivity, sharedViewModel);
        fragmentFavouriteBinding = FragmentFavouriteBinding.inflate(inflater, container, false);
        favouriteViewModel = new FavouriteViewModel();
        fragmentFavouriteBinding.setFavouriteViewModel(favouriteViewModel);
        fragmentFavouriteBinding.executePendingBindings();
        return fragmentFavouriteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switchAdapter = new SwitchAdapter(homeActivity, sharedViewModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeActivity.getApplicationContext());
        fragmentFavouriteBinding.rcShowListWitchFavourite.setLayoutManager(linearLayoutManager);
        sharedViewModel.getListFavourSwitch().observe(homeActivity, new Observer<List<SwitchModel>>() {
            @Override
            public void onChanged(List<SwitchModel> list) {
                if (list != null) {
                    switchAdapter.setData(list);
                    fragmentFavouriteBinding.rcShowListWitchFavourite.post(new Runnable()
                    {
                        @Override
                        public void run() {
                            switchAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        fragmentFavouriteBinding.rcShowListWitchFavourite.setAdapter(switchAdapter);
    }
}
