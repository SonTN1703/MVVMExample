package com.example.projecttraining.login;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecttraining.common.database.DatabaseTableUserManager;
import com.example.projecttraining.models.User;

public class LoginViewModel extends ViewModel implements IGetData{
    public static final int LOGIN = 1;
    public static final int REGISTER = 3;
    DatabaseTableUserManager databaseTableUserManager;
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> userPassword = new MutableLiveData<>();
    public MutableLiveData<Integer> action = new MutableLiveData<>();
    private MutableLiveData<User> userMutableLiveData;

    public LoginViewModel() {

    }
    public void setDatabaseTableUserManager(DatabaseTableUserManager databaseTableUserManager){
        this.databaseTableUserManager = databaseTableUserManager;
    }
    public LiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public LiveData<Integer> getAction() {
        return action;
    }


    public void onLoginClicked() {
        action.postValue(LOGIN);
        User user = new User();
        user.setUserName(userName.getValue());
        user.setPassword(userPassword.getValue());
        databaseTableUserManager.open();
        new CheckUserAsyncTask(databaseTableUserManager,this).execute(user);

    }

    public void onRegisterClicked() {
        action.setValue(REGISTER);
    }

    @Override
    public void setUser(User user) {
        userMutableLiveData.postValue(user);
    }

    private static class CheckUserAsyncTask extends AsyncTask<User, User, User> {
        DatabaseTableUserManager databaseTableUserManager;
        IGetData getData;

        public CheckUserAsyncTask(DatabaseTableUserManager databaseTableUserManager,IGetData getData) {
            this.databaseTableUserManager = databaseTableUserManager;
            this.getData = getData;
        }

        @Override
        protected User doInBackground(User... users) {
            return databaseTableUserManager.searchUser(users[0]);
        }

        @Override
        protected void onProgressUpdate(User... values) {

        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            getData.setUser(user);
            databaseTableUserManager.close();
        }
    }
}
