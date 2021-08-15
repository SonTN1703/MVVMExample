package com.example.projecttraining.login;

import android.content.Intent;
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

import com.example.projecttraining.HomeActivity;
import com.example.projecttraining.MainActivity;
import com.example.projecttraining.common.FragmentNavigate;
import com.example.projecttraining.common.database.DatabaseTableUserManager;
import com.example.projecttraining.databinding.FragmentLoginBinding;
import com.example.projecttraining.models.User;
import com.example.projecttraining.register.FragmentRegister;

public class FragmentLogin extends Fragment {

    private MainActivity mainActivity;
    private FragmentLoginBinding fragmentLoginBinding;
    private LoginViewModel loginViewModel;

    public static FragmentLogin newInstance() {
        Bundle args = new Bundle();
        FragmentLogin fragmentLogin = new FragmentLogin();
        fragmentLogin.setArguments(args);
        return fragmentLogin;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        loginViewModel = new LoginViewModel();
        fragmentLoginBinding.setLoginViewModel(loginViewModel);
        fragmentLoginBinding.executePendingBindings();
        DatabaseTableUserManager databaseTableUserManager = new DatabaseTableUserManager(mainActivity.getApplicationContext());
        loginViewModel.setDatabaseTableUserManager(databaseTableUserManager);
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel.getAction().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer action) {
                if (action != 0) {
                    OnActionClickListener(action);
                }
            }
        });
    }

    private void OnActionClickListener(int action) {
        switch (action) {
            case LoginViewModel.LOGIN:
                loginViewModel.getUser().observe(getActivity(), new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if(user!=null){
                            Toast.makeText(getActivity(),"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mainActivity, HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getActivity(),"Dang nhap that bai",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case LoginViewModel.REGISTER:
                FragmentRegister fragmentRegister = FragmentRegister.newInstance();
                FragmentNavigate.addNewFragment(mainActivity, fragmentRegister);
                break;
        }
    }
}
