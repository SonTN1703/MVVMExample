package com.example.projecttraining.models;

public class User {
    private String email = "";
    private String sdt = "";
    private String userName = "";
    private String password = "";

    public User() {
    }

    public User(String email, String sdt, String userName, String password) {
        this.email = email;
        this.sdt = sdt;
        this.userName = userName;
        this.password = password;
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
}
