package com.example.projecttraining.models;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private int id;
    private String email = "";
    private String sdt = "";
    private String userName = "";
    private String password = "";

    public User() {
    }

    public User(int id, String email, String sdt, String userName, String password) {
        this.id = id;
        this.email = email;
        this.sdt = sdt;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean passwordValidation() {
        if (password.length() >= 8) {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);
            return hasLetter.find() && hasDigit.find() && !hasSpecial.find();
        } else
            return false;

    }
}
