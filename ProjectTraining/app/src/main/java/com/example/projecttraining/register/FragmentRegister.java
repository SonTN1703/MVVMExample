package com.example.projecttraining.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.projecttraining.MainActivity;
import com.example.projecttraining.common.FragmentNavigate;
import com.example.projecttraining.common.database.DatabaseTableUserManager;
import com.example.projecttraining.databinding.FragmentRegisterBinding;

public class FragmentRegister extends Fragment {
    private MainActivity mainActivity;
    private FragmentRegisterBinding fragmentRegisterBinding;
    private RegisterViewModel registerViewModel;
    private DatabaseTableUserManager databaseTableUserManager;

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
        mainActivity = (MainActivity) getActivity();
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        registerViewModel = new RegisterViewModel();
        fragmentRegisterBinding.setRegisterViewModel(registerViewModel);
        fragmentRegisterBinding.executePendingBindings();
        return fragmentRegisterBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerViewModel.setRandomCapcha();
        databaseTableUserManager = new DatabaseTableUserManager(getContext());
        registerViewModel.setDbManager(databaseTableUserManager);
        registerViewModel.getActive().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer action) {
                if (action!=0){
                    OnRegisterClickListener(action);
                }else {
                    Toast.makeText(getContext(),"xay ra loi",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void OnRegisterClickListener(int action) {
        switch (action) {
            case RegisterViewModel.ERROR_CAPCHA:
                Toast.makeText(getContext(), "Mã capcha sai", Toast.LENGTH_SHORT).show();
                break;
            case RegisterViewModel.ERROR_PASSWORD:
                Toast.makeText(getContext(), "Password khong dung dinh dang", Toast.LENGTH_SHORT).show();
                break;
            case RegisterViewModel.ERROR_EMAIL:
                Toast.makeText(getContext(), "Email da ton tai", Toast.LENGTH_SHORT).show();
                break;
            case RegisterViewModel.ERROR_USERNAME:
                Toast.makeText(getContext(), "username da ton tai", Toast.LENGTH_SHORT).show();
                break;
            case RegisterViewModel.SUCCESS:
                Toast.makeText(getContext(), "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                FragmentNavigate.removeFragmentRegister(mainActivity);
                break;
        }
    }

}
