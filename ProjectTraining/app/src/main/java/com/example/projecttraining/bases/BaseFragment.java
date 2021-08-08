package com.example.projecttraining.bases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.MainActivity;

public abstract class BaseFragment extends Fragment {

    protected View view;
    protected MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutResource(), container, false);
        mainActivity = (MainActivity) getActivity();
        return view;
    }

    protected abstract int getLayoutResource();
}
