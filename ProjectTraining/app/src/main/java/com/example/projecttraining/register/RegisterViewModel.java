package com.example.projecttraining.register;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.projecttraining.BR;
import com.example.projecttraining.models.User;

public class RegisterViewModel extends BaseObservable {
    private User user = new User();

    @Bindable
    public String getEmail(){
        return user.getEmail();
    }
    @Bindable
    public String getPassword(){
        return user.getPassword();
    }
    @Bindable
    public String getSdt(){
        return user.getSdt();
    }
    @Bindable
    public String getUserName(){
        return user.getUserName();
    }
    public void setEmail(String email){
        user.setEmail(email);
        notifyPropertyChanged(BR.email);
    }
    public void setUserName(String userName){
        user.setUserName(userName);
        notifyPropertyChanged(BR.userName);
    }
    public void setSdt(String sdt){
        user.setUserName(sdt);
        notifyPropertyChanged(BR.sdt);
    }
    public void setPassword(String password){
        user.setPassword(password);
        notifyPropertyChanged(BR.password);
    }
}
