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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.HomeActivity;
import com.example.projecttraining.MainActivity;
import com.example.projecttraining.R;
import com.example.projecttraining.common.FragmentNavigate;
import com.example.projecttraining.databinding.FragmentLoginBinding;
import com.example.projecttraining.bases.BaseFragment;
import com.example.projecttraining.databinding.FragmentRegisterBinding;
import com.example.projecttraining.register.FragmentRegister;

public class FragmentLogin extends Fragment {

    private MainActivity mainActivity;
    private FragmentLoginBinding fragmentLoginBinding;

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
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false);
        fragmentLoginBinding.setLoginViewModel(new LoginViewModel());
        fragmentLoginBinding.executePendingBindings();
        mainActivity = (MainActivity) getActivity();
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentLoginBinding.btnRegister.setOnClickListener(v -> {
            FragmentRegister fragmentRegister = FragmentRegister.newInstance();
            FragmentNavigate.addNewFragment(mainActivity, fragmentRegister);
        });
    }

    protected int getLayoutResource() {
        return R.layout.fragment_login;
    }

    @BindingAdapter({"toastMessage"})
    public static void showToast(View view, String message) {
        if (message != null) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }

    }
}
