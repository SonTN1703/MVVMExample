package com.example.projecttraining.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.MainActivity;
import com.example.projecttraining.R;
import com.example.projecttraining.bases.BaseFragment;
import com.example.projecttraining.databinding.FragmentRegisterBinding;

public class FragmentRegister extends Fragment {
    private MainActivity mainActivity;
    private FragmentRegisterBinding fragmentRegisterBinding;

    public static FragmentRegister newInstance() {
        Bundle args = new Bundle();
        FragmentRegister fragmentRegister = new FragmentRegister();
        fragmentRegister.setArguments(args);
        return fragmentRegister;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        fragmentRegisterBinding.setRegisterViewModel(new RegisterViewModel());
        fragmentRegisterBinding.executePendingBindings();
        mainActivity = (MainActivity) getActivity();
        return fragmentRegisterBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
