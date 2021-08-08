package com.example.projecttraining.login;

import android.app.Activity;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.projecttraining.BR;
import com.example.projecttraining.MainActivity;
import com.example.projecttraining.common.FragmentNavigate;
import com.example.projecttraining.models.User;
import com.example.projecttraining.register.FragmentRegister;

public class LoginViewModel extends BaseObservable {
    private User user = new User();
    private String successMessage = "Dang nhap thanh cong";
    private String errorMessage = "Dang nhap that bai";
    private MainActivity activity;

    public LoginViewModel() {
    }

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUserName() {
        return user.getUserName();
    }

    @Bindable
    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserName(String userName) {
        user.setUserName(userName);
        notifyPropertyChanged(BR.userName);
    }

    public void setUserPassword(String password) {
        user.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public void onLoginClicked() {
        if (isInputDataValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    public void onRegisterClicked() {
        FragmentRegister fragmentRegister = FragmentRegister.newInstance();
        FragmentNavigate.addNewFragment(activity, fragmentRegister);
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getUserName()) && getUserPassword().length() > 5;
    }
}
