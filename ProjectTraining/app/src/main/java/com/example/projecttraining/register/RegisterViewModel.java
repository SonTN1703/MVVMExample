package com.example.projecttraining.register;


import android.os.AsyncTask;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecttraining.common.database.DatabaseTableUserManager;
import com.example.projecttraining.models.User;

import java.util.Random;

public class RegisterViewModel extends ViewModel implements ISuccessListener {
    public static final int ERROR_CAPCHA = 1;
    public static final int ERROR_EMAIL = 3;
    public static final int ERROR_PASSWORD = 2;
    public static final int ERROR_USERNAME = 4;
    public static final int SUCCESS = 5;
    private User user = new User();
    private String characters = "ABCDEFGHJKLMNOPQRSTUVWXYZ";
    public ObservableField<String> capcha = new ObservableField<>();
    private DatabaseTableUserManager dbManager;
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> sdt = new MutableLiveData<>();
    public MutableLiveData<String> confirmCapcha = new MutableLiveData<>();
    private MutableLiveData<Integer> active = new MutableLiveData<>();

    public RegisterViewModel() {
    }

    public MutableLiveData<Integer> getActive() {
        return active;
    }

    public void setDbManager(DatabaseTableUserManager databaseTableUserManager) {
        dbManager = databaseTableUserManager;
    }

    public void setRandomCapcha() {
        Random random = new Random();
        String randomCapcha = "";
        char text[] = new char[4];
        for (int i = 0; i < 4; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++) {
            randomCapcha += text[i];
        }
        capcha.set(randomCapcha);
    }

    public void confirmOnClicked() {
        user.setUserName(username.getValue());
        user.setEmail(email.getValue());
        user.setSdt(sdt.getValue());
        user.setPassword(password.getValue());
        dbManager.open();
        if (!confirmCapcha.getValue().equalsIgnoreCase(capcha.get())) {
            active.postValue(ERROR_CAPCHA);
        } else if (!user.passwordValidation()) {
            active.postValue(ERROR_PASSWORD);
        } else {
            new RegisterAsyncTask(dbManager, this).execute(user);
        }
        dbManager.close();
    }

    @Override
    public void setActive(int isSuccess) {
        active.postValue(isSuccess);
    }

    public static class RegisterAsyncTask extends AsyncTask<User, Void, Integer> {

        DatabaseTableUserManager databaseTableUserManager;
        ISuccessListener iSuccessListener;

        public RegisterAsyncTask(DatabaseTableUserManager databaseTableUserManager, ISuccessListener iSuccessListener) {
            this.databaseTableUserManager = databaseTableUserManager;
            this.iSuccessListener = iSuccessListener;
        }

        @Override
        protected Integer doInBackground(User... users) {
            User user = users[0];
            int active = 0;
            if (!databaseTableUserManager.searchUserByName(user)) {
                active = RegisterViewModel.ERROR_USERNAME;
            } else if (!databaseTableUserManager.searchUserByEmail(user)) {
                active = RegisterViewModel.ERROR_EMAIL;
            } else if (databaseTableUserManager.insertUser(user)) {
                active = RegisterViewModel.SUCCESS;
            }
            return active;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            iSuccessListener.setActive(integer);
        }
    }
}
